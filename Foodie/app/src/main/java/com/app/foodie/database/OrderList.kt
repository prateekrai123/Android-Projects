package com.app.foodie.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "OrderList")
data class OrderList (

    @PrimaryKey
    val resId : String,

    @ColumnInfo(name = "food_items")
    val foodItems : String

)