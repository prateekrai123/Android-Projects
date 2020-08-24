package com.app.foodie.activity

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.app.foodie.R
import com.app.foodie.adapter.RestaurantInfoAdapter
import com.app.foodie.dataClass.RestaurantsInfoClass
import com.app.foodie.database.DataBase
import com.app.foodie.database.OrderList
import com.google.gson.Gson
import org.json.JSONArray

class RestaurantInfo : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var layoutManager : RecyclerView.LayoutManager
    lateinit var recyclerAdapter : RestaurantInfoAdapter
    private val orderList : ArrayList<RestaurantsInfoClass> = arrayListOf()
    lateinit var addToCart : Button
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_info)

        val restaurantInfoList : ArrayList<RestaurantsInfoClass> = arrayListOf()

        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        addToCart = findViewById(R.id.addToCart)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Food Items"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerAdapter = RestaurantInfoAdapter(this, restaurantInfoList,
        object : RestaurantInfoAdapter.onButtonClickListener{
            override fun onAddClickListener(itemObject: RestaurantsInfoClass) {
                orderList.add(itemObject)
                if(orderList.size>0){
                    RestaurantInfoAdapter.isCartEmpty = false
                    addToCart.visibility = View.VISIBLE
                }
            }

            override fun onRemoveClickListener(itemObject: RestaurantsInfoClass) {
                orderList.remove(itemObject)
                if(orderList.size==0){
                    RestaurantInfoAdapter.isCartEmpty = true
                    addToCart.visibility = View.INVISIBLE
                }
            }

        })

        var id = "1"
        var resName = ""

        if(intent != null){
            id = intent.getBundleExtra("bundle").getString("id") as String
            resName = intent.getBundleExtra("bundle").getString("name") as String
        }

        val queue = Volley.newRequestQueue(this)
        val url = "http://13.235.250.119/v2/restaurants/fetch_result/$id"

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { data ->
                val dataMain = data.getJSONObject("data")
                println(dataMain)
                val success = dataMain.getBoolean("success")
                if(success){
                    val data = dataMain.getJSONArray("data")
                    for(i in 0 until data.length()){
                        val resInfoJSOnObject = data.getJSONObject(i)
                        val resInfoObject = RestaurantsInfoClass(
                            resInfoJSOnObject.getString("id"),
                            resInfoJSOnObject.getString("name"),
                            resInfoJSOnObject.getString("cost_for_one")
                        )
                        restaurantInfoList.add(resInfoObject)
//                        recyclerAdapter = RestaurantInfoAdapter(this, restaurantInfoList)
                        recyclerView.adapter = recyclerAdapter
                        recyclerView.layoutManager = layoutManager
                        recyclerView.addItemDecoration(
                            DividerItemDecoration(this,
                                (layoutManager as LinearLayoutManager).orientation
                            )
                        )
                    }
                }
                else{
                    Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_SHORT).show()
            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "9bf534118365f1"
                return headers
            }
        }
        queue.add(jsonObjectRequest)

        addToCart.setOnClickListener {
            val gson = Gson()
            val orderListJson = gson.toJson(orderList)
            val async = OrderItem(this, id, orderListJson, 1).execute()
            val success = async.get()
            if(success){
                val cartIntent = Intent(this, CartActivity::class.java)
                cartIntent.putExtra("resId", id)
                cartIntent.putExtra("resName", resName)
                startActivity(cartIntent)
            }
        }
    }

    class OrderItem(context : Context, private val restaurantId : String, private val foodItems : String, private val mode : Int) :
        AsyncTask<Void, Void, Boolean>(){

        val db = Room.databaseBuilder(context, DataBase::class.java, "order-database").build()

        override fun doInBackground(vararg params: Void?): Boolean {

            when(mode){
                1 -> {
                    db.orderDao().insertOrder(OrderList(restaurantId, foodItems))
                    db.close()
                    return true
                }
                2 -> {
                    db.orderDao().deleteOrder(OrderList(restaurantId, foodItems))
                    db.close()
                    return true
                }
            }
            return false
        }

    }
}