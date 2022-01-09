package com.example.restaurantapp.domain

data class RestaurantMainItem(
    val restaurantId: Int = 0,
    val restaurantName: String = "",
    val neighborhood: String = "",
    val foodItems: String = "",
    val rating: Float = 0.0f,
    val cuisineType: String = ""
)