package com.app.foodie.fragment

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.provider.DocumentsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.app.foodie.R
import com.app.foodie.adapter.RecyclerAdapter
import com.app.foodie.dataClass.Restaurants
import com.app.foodie.database.DataBase
import com.app.foodie.database.RestaurantEntity

class FavouriteRestaurants : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    var restaurants : ArrayList<Restaurants> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourite_restaurants, container, false)

        val resList = FavResAsync(activity as Context).execute().get()

        for(i in resList){
            restaurants.add(
                Restaurants(
                    i.id.toString(),
                    i.name,
                    i.rating,
                    i.costForOne,
                    i.imageUrl
                )
            )
        }

        layoutManager = LinearLayoutManager(activity as Context)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RecyclerAdapter(activity as Context, restaurants)
        recyclerView.addItemDecoration(
            DividerItemDecoration(activity as Context,
                (layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)

        return view
    }

    class FavResAsync(context: Context) : AsyncTask<Void,Void,List<RestaurantEntity>>(){
        private val db = Room.databaseBuilder(context, DataBase::class.java, "order-database").build()
        override fun doInBackground(vararg params: Void?): List<RestaurantEntity>? {
            return db.restaurantDao().getAllRestaurants()
        }
    }
}