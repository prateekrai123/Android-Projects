package com.app.foodie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.foodie.R
import com.app.foodie.dataClass.RestaurantsInfoClass

class CartAdapter(val context : Context, private val orderList : ArrayList<RestaurantsInfoClass>) : RecyclerView.Adapter<CartAdapter.DashBoardViewHolder>() {

    class DashBoardViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val name : TextView = view.findViewById(R.id.name)
        val cost : TextView = view.findViewById(R.id.cost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_adapter_view,parent , false)

        return DashBoardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        holder.name.text = orderList[position].name
        holder.cost.text = "Rs. ${orderList[position].price}"
    }

}