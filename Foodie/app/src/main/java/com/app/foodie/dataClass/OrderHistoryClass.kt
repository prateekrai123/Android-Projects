package com.app.foodie.dataClass

data class OrderHistoryClass (
    val orderId : String,
    val resName : String,
    val cost : String,
    val orderPlacedAt : String,
    val orderList : ArrayList<FoodItems>
)