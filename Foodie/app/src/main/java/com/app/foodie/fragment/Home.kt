package com.app.foodie.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.app.foodie.R
import com.app.foodie.adapter.RecyclerAdapter
import com.app.foodie.dataClass.Restaurants

class Home : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager : RecyclerView.LayoutManager
    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val restaurantList : ArrayList<Restaurants> = arrayListOf()



        recyclerView = view.findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(activity)


        val queue = Volley.newRequestQueue(activity as Context)
        val url = "http://13.235.250.119/v2/Restaurants/fetch_result/"


        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET ,url, null,
            Response.Listener {

                val dataMain = it.getJSONObject("data")

                val success = dataMain.getBoolean("success")
                if(success){
                    val data = dataMain.getJSONArray("data")
                    for(i in 0 until data.length()){
                        val restaurantJsonObject = data.getJSONObject(i)
                        val restaurantObject = Restaurants(
                            restaurantJsonObject.getString("id"),
                            restaurantJsonObject.getString("name"),
                            restaurantJsonObject.getString("rating"),
                            restaurantJsonObject.getString("cost_for_one"),
                            restaurantJsonObject.getString("image_url")
                        )
                        restaurantList.add(restaurantObject)
                        recyclerAdapter = RecyclerAdapter(activity as Context, restaurantList)
                        recyclerView.adapter = recyclerAdapter
                        recyclerView.layoutManager = layoutManager
                        recyclerView.addItemDecoration(
                            DividerItemDecoration(
                                recyclerView.context,
                                (layoutManager as LinearLayoutManager).orientation
                            )
                        )
                    }
                }
                else{
                    Toast.makeText(activity as Context, "Error occurred", Toast.LENGTH_SHORT).show()
                }

        }, Response.ErrorListener {

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