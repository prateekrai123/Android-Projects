package com.app.foodie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.foodie.R
import com.app.foodie.dataClass.Restaurants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recylcler_data.view.*

class RecyclerAdapter(val context : Context, val restaurantList : ArrayList<Restaurants>) : RecyclerView.Adapter<RecyclerAdapter.DashboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recylcler_data, parent, false)

        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.txtName.text = restaurant.name
        holder.costForOne.text = restaurant.cost_for_one
        holder.rating.text = restaurant.rating
        Picasso.get().load(restaurant.image).into(holder.imageView)
    }

    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtName : TextView = view.findViewById(R.id.name)
        val imageView : ImageView = view.findViewById(R.id.image)
        val costForOne : TextView = view.findViewById(R.id.cost_for_one)
        val rating : TextView = view.findViewById(R.id.rating)
    }
}