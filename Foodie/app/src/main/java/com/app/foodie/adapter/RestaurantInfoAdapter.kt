package com.app.foodie.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.foodie.R
import com.app.foodie.activity.CartActivity
import com.app.foodie.activity.RestaurantInfo
import com.app.foodie.dataClass.RestaurantsInfoClass

class RestaurantInfoAdapter(val context : Context, private val restaurantsInfoList : ArrayList<RestaurantsInfoClass>, private val listener : onButtonClickListener) : RecyclerView.Adapter<RestaurantInfoAdapter.DashBoardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_info_data, parent, false)

        return DashBoardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  restaurantsInfoList.size
    }

    companion object{
        var isCartEmpty = true
    }

    interface onButtonClickListener{
        fun onAddClickListener(itemObject : RestaurantsInfoClass)
        fun onRemoveClickListener(itemObject: RestaurantsInfoClass)
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        var temp = 0
        val item = restaurantsInfoList[position]
        holder.name.text = item.name
        holder.cost.text = item.price
        holder.add.setOnClickListener {
            listener.onAddClickListener(item)
            holder.add.visibility = View.INVISIBLE
            holder.remove.visibility = View.VISIBLE
        }
        holder.remove.setOnClickListener {
            listener.onRemoveClickListener(item)
            holder.remove.visibility = View.INVISIBLE
            holder.add.visibility = View.VISIBLE
        }
        holder.serialNumber.text = temp++.toString()
    }

    class DashBoardViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val serialNumber : TextView = view.findViewById(R.id.serialNumber)
        val name : TextView = view.findViewById(R.id.name)
        val cost : TextView = view.findViewById(R.id.cost)
        val add : Button = view.findViewById(R.id.add)
        val remove : Button = view.findViewById(R.id.remove)
    }

}