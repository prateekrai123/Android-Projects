package com.app.foodie.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.app.foodie.R
import com.app.foodie.adapter.OrderAdapter
import com.app.foodie.adapter.RecyclerAdapter
import com.app.foodie.dataClass.FoodItems
import com.app.foodie.dataClass.OrderHistoryClass
import com.app.foodie.database.OrderList
import com.google.gson.JsonObject
import kotlinx.android.synthetic.*

class OrderHistory : Fragment() {

    lateinit var recyclerView : RecyclerView
    lateinit var recyclerAdapter: OrderAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    val orderArrayList : ArrayList<OrderHistoryClass> = arrayListOf()
    lateinit var progressLayout : RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_order_history, container, false)

        val userId = (activity as Context).getSharedPreferences(getString(R.string.sharedPreferences), Context.MODE_PRIVATE).getString("user_id", "")           //jsonParams.put("user_id", this.getSharedPreferences(getString(R.string.sharedPreferences),Context.MODE_PRIVATE).getString("user_id", "1") as String)

        progressLayout = view.findViewById(R.id.progressLayout)
        recyclerView = view.findViewById(R.id.recyclerView)

        val queue = Volley.newRequestQueue(activity as Context)
        val url = "http://13.235.250.119/v2/orders/fetch_result/$userId"

        val foodItems : ArrayList<FoodItems> = arrayListOf()

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null, Response.Listener {
            Toast.makeText(activity as Context, "$it", Toast.LENGTH_LONG).show()
            val data = it.getJSONObject("data")
            val success = data.getBoolean("success")
            if(success){
                progressLayout.visibility = View.INVISIBLE
                val dataMain = data.getJSONArray("data")
                for(i in 0 until dataMain.length()){
                    val resObject = dataMain.getJSONObject(i)
                    val foodItem = resObject.getJSONArray("food_items")
                    for(i in 0 until foodItem.length()){
                        val food  = foodItem.getJSONObject(i)
                        foodItems.add(
                            FoodItems(
                                food.getString("food_item_id"),
                                food.getString("name"),
                                food.getString("cost")
                            )
                        )
                    }
                    orderArrayList.add(
                        OrderHistoryClass(
                            resObject.getString("order_id"),
                            resObject.getString("restaurant_name"),
                            resObject.getString("total_cost"),
                            resObject.getString("order_placed_at"),
                            foodItems
                        )
                    )
                }
                layoutManager = LinearLayoutManager(activity as Context)
                recyclerAdapter = OrderAdapter(activity as Context, orderArrayList)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = recyclerAdapter
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.addItemDecoration(
                    DividerItemDecoration(activity as Context,
                        (layoutManager as LinearLayoutManager).orientation
                    )
                )
            }
            else{
                progressLayout.visibility = View.INVISIBLE
                Toast.makeText(activity as Context, "Error occurred $it", Toast.LENGTH_SHORT).show()
            }
        },Response.ErrorListener {
            progressLayout.visibility = View.INVISIBLE
            Toast.makeText(activity as Context, "Volley Error $it", Toast.LENGTH_SHORT).show()
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "9bf534118365f1"
                return headers
            }
        }
        queue.add(jsonObjectRequest)
        return view
    }
}