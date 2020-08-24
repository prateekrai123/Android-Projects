package com.app.foodie.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RestaurantDao {

    @Insert
    fun insertRestaurant(restaurantEntity: RestaurantEntity)

    @Delete
    fun deleteRestaurant(restaurantEntity: RestaurantEntity)

    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants() : List<RestaurantEntity>

    @Query("SELECT * FROM restaurants where id = :resId")
    fun getRestaurantsById(resId : String) : RestaurantEntity

}