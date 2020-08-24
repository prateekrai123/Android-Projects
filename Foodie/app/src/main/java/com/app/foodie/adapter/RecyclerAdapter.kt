package com.app.foodie.adapter

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import androidx.room.Room
import com.app.foodie.R
import com.app.foodie.activity.RestaurantInfo
import com.app.foodie.dataClass.Restaurants
import com.app.foodie.database.DataBase
import com.app.foodie.database.RestaurantEntity
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recylcler_data.view.*

class RecyclerAdapter(val context : Context, private val restaurantList : ArrayList<Restaurants>) : RecyclerView.Adapter<RecyclerAdapter.DashBoardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recylcler_data, parent, false)

        return DashBoardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val listofav = GetFavAsyncTask(context).execute().get()
        val restaurant = restaurantList[position]
        if(listofav.isNotEmpty() && listofav.contains(restaurant.id)) {
            holder.imgFav.setImageResource(R.drawable.ic_action_favourites2)
        }
        else {
            holder.imgFav.visibility = View.VISIBLE
        }
        holder.txtName.text = restaurant.name
        holder.costForOne.text = restaurant.cost_for_one
        holder.rating.text = restaurant.rating
        Picasso.get().load(restaurant.image).into(holder.imageView)
        holder.content.setOnClickListener {
            val intent = Intent(context, RestaurantInfo::class.java)
            val bundle = Bundle()
            bundle.putString("id", restaurant.id)
            bundle.putString("name", restaurant.name)
            intent.putExtra("bundle", bundle)
            startActivity(context, intent, null)
        }
        holder.imgFav.setOnClickListener {
            val restaurantEntity = RestaurantEntity(
                restaurant.id.toInt(),
                restaurant.name,
                restaurant.rating,
                restaurant.cost_for_one,
                restaurant.image
            )
            if(!DBAsyncTask(context, restaurantEntity, 1).execute().get()){
                val async = DBAsyncTask(context, restaurantEntity, 2).execute()
                val data = async.get()
                if(data){
                    Toast.makeText(context, "Added to favourites", Toast.LENGTH_SHORT).show()
                    holder.imgFav.setImageResource(R.drawable.ic_action_favourites2)
                }
                else{
                    val async = DBAsyncTask(context, restaurantEntity, 3).execute()
                    val data = async.get()
                    if(data){
                        Toast.makeText(context, "Removed from favourites", Toast.LENGTH_SHORT).show()
                        holder.imgFav.setImageResource(R.drawable.ic_action_favourites1)
                    }
                }
            }

        }
    }

    class DashBoardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtName : MaterialTextView = view.findViewById(R.id.name)
        val imageView : ImageView = view.findViewById(R.id.image)
        val costForOne : MaterialTextView = view.findViewById(R.id.cost_for_one)
        val rating : MaterialTextView = view.findViewById(R.id.rating)
        val content : RelativeLayout = view.findViewById(R.id.content)
        val imgFav : ImageView = view.findViewById(R.id.favourite1)
    }
}

class GetFavAsyncTask(context: Context) : AsyncTask<Void,Void,List<String>>(){
    private val db = Room.databaseBuilder(context, DataBase::class.java, "order-database").build()
    override fun doInBackground(vararg params: Void?): List<String> {
        val list = db.restaurantDao().getAllRestaurants()
        val llist = arrayListOf<String>()
        for(i in list){
            llist.add(i.id.toString())
        }
        return llist
    }
}

class DBAsyncTask(context: Context, private val restaurantEntity: RestaurantEntity,private val mode : Int) : AsyncTask<Void,Void,Boolean>(){

    private val db = Room.databaseBuilder(context, DataBase::class.java, "order-database").build()

    override fun doInBackground(vararg params: Void?): Boolean {
        when(mode){
            1 -> {
                val res : RestaurantEntity? =
                    db.restaurantDao().getRestaurantsById(restaurantEntity.id.toString())
                db.close()
                return res != null
            }
            2 -> {
                db.restaurantDao().insertRestaurant(restaurantEntity)
                db.close()
                return true
            }
            3 -> {
                db.restaurantDao().deleteRestaurant(restaurantEntity)
                db.close()
                return true
            }
        }
        return true
    }
}