package com.app.foodie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.foodie.R
import com.app.foodie.activity.RestaurantInfo
import com.app.foodie.dataClass.FoodItems
import com.app.foodie.dataClass.OrderHistoryClass
import com.app.foodie.dataClass.RestaurantsInfoClass
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class OrderAdapter(val context: Context, val foodItems : ArrayList<OrderHistoryClass>) : RecyclerView.Adapter<OrderAdapter.DashBoardViewHolder>() {

    class DashBoardViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val resName : TextView = view.findViewById(R.id.resName)
        val date : TextView  = view.findViewById(R.id.date)
        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_history_recycler, parent, false)
        return DashBoardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodItems.size
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val order = foodItems[position]
        holder.resName.text = order.resName
        holder.date.text = formatDate(order.orderPlacedAt)
        setUpRecyclerView(holder.recyclerView, order)
    }

    private fun setUpRecyclerView(recyclerView: RecyclerView, order : OrderHistoryClass){
        val foodItems = ArrayList<RestaurantsInfoClass>()
        for(i in 0 until order.orderList.size){
            val food = order.orderList[i]
            foodItems.add(
                RestaurantsInfoClass(
                    food.id,
                    food.name,
                    food.cost
                )
            )
        }
        val cartAdapter = CartAdapter(context, foodItems)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = cartAdapter
    }

    private fun formatDate(dateString : String) : String?{
        val type = SimpleDateFormat("dd-MM-yy HH:mm:ss", Locale.ENGLISH)
        val date = type.parse(dateString) as Date
        return (SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(date))
    }

}