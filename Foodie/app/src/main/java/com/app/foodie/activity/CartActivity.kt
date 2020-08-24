package com.app.foodie.activity

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.app.foodie.R
import com.app.foodie.adapter.CartAdapter
import com.app.foodie.dataClass.RestaurantsInfoClass
import com.app.foodie.database.DataBase
import com.app.foodie.database.OrderList
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import java.sql.Connection

class CartActivity : AppCompatActivity() {

    lateinit var resName : TextView
    lateinit var placeOrder : Button
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: CartAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var toolbar: Toolbar
    private var orderList : ArrayList<RestaurantsInfoClass> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        var id = ""
        var name = ""

        resName = findViewById(R.id.resName)
        if(intent!=null){
            id = intent.getStringExtra("resId")
            name = intent.getStringExtra("resId")
        }
        resName.text = intent.getStringExtra("name")
        placeOrder = findViewById(R.id.placeOrder)
        recyclerView = findViewById(R.id.recyclerView)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "My Cart"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val list = GetItemsDBAsync(this).execute().get()

        for(element in list){
            orderList.addAll(Gson().fromJson(element.foodItems, Array<RestaurantsInfoClass>::class.java).asList())
        }
        
        layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CartAdapter(this, orderList)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerAdapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(
            DividerItemDecoration(this,
                (layoutManager as LinearLayoutManager).orientation
            )
        )

        var totalPrice = 0

        for(i in orderList){
            totalPrice += Integer.parseInt(i.price)
        }

        placeOrder.text = "Place Order : Rs. $totalPrice"

        placeOrder.setOnClickListener {
            val url = "http://13.235.250.119/v2/place_order/fetch_result/"
            val queue = Volley.newRequestQueue(this)

            val jsonParams = JSONObject()
            jsonParams.put("user_id", this.getSharedPreferences(getString(R.string.sharedPreferences),Context.MODE_PRIVATE).getString("user_id", "1") as String)
            jsonParams.put("restaurant_id", id)
            jsonParams.put("total_cost", totalPrice)

            val itemsArray = JSONArray()
            for (i in 0 until orderList.size){
                val itemId = JSONObject()
                itemId.put("item_id", orderList[i].id)
                itemsArray.put(i, itemId)
            }
            println(jsonParams)
            jsonParams.put("food", itemsArray)
            val jsonObjectRequest = object : JsonObjectRequest(Request.Method.POST, url, jsonParams,Response.Listener {
                val data = it.getJSONObject("data")
                val success = data.getBoolean("success")
                if(success){
                    ClearDBAsync(this, id)
                    val finishIntent = Intent(this, OrderPlacedActivity::class.java)
                    startActivity(finishIntent)
                }
                else{
                    Toast.makeText(this, "Error occurred $it", Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener {
                Toast.makeText(this, "Volley Error occurred $it", Toast.LENGTH_SHORT).show()
            }){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Content-type"] = "application/json"
                    headers["token"] = "9bf534118365f1"
                    return headers
                }
            }
            queue.add(jsonObjectRequest)
        }
    }

    override fun onBackPressed() {
        if(orderList.isEmpty()){
            super.onBackPressed()
            finish()
        }
        else{ 
            
        }
    }

    class GetItemsDBAsync(context : Context) : AsyncTask<Void,Void,List<OrderList>>(){
        private val db = Room.databaseBuilder(context, DataBase::class.java, "order-database").build()
        override fun doInBackground(vararg params: Void?): List<OrderList> {
            return db.orderDao().getAllOrders()
        }

    }

    class ClearDBAsync(context : Context, val resId : String) : AsyncTask<Void,Void,Boolean>(){
        private val db = Room.databaseBuilder(context, DataBase::class.java, "order-database").build()
        override fun doInBackground(vararg params: Void?): Boolean {
            db.orderDao().deleteAllOrders(resId)
            db.close()
            return true
        }
    }
}