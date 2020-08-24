package com.app.foodie.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OrderList::class, RestaurantEntity::class], version = 1)
abstract class DataBase : RoomDatabase(){

    abstract fun orderDao() : OrderDao

    abstract fun restaurantDao() : RestaurantDao

}