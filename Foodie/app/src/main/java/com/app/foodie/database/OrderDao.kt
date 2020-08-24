package com.app.foodie.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao{

    @Insert
    fun insertOrder(orderList: OrderList)

    @Delete
    fun deleteOrder(orderList: OrderList)

    @Query("SELECT * FROM OrderList")
    fun getAllOrders() : List<OrderList>

    @Query("DELETE FROM OrderList WHERE resId = :resId")
    fun deleteAllOrders(resId : String)

}