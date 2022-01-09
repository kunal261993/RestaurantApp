package com.example.restaurantapp.utils

import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.restaurantapp.domain.RestaurantMainItem

@BindingAdapter("restaurantName")
fun TextView.setRestaurantName(item: RestaurantMainItem?) {
    item?.let {
        text = item.restaurantName
    }
}

@BindingAdapter("cuisineType")
fun TextView.setCuisineType(item: RestaurantMainItem?) {
    item?.let {
        text = item.cuisineType
    }
}

@BindingAdapter("foodItems")
fun TextView.setFoodItems(item: RestaurantMainItem?) {
    item?.let {
        text = item.foodItems
    }
}

@BindingAdapter("ratingNumber")
fun TextView.setRatingNumber(item: RestaurantMainItem?) {
    item?.let {
        text = item.rating.toString()

    }
}

@BindingAdapter("neighbourhood")
fun TextView.setNeighbourhood(item: RestaurantMainItem?) {
    item?.let {
        text = item.neighborhood
    }
}

@BindingAdapter("restaurantRating")
fun RatingBar.setRestaurantRating(item: RestaurantMainItem?) {
    item?.let {
        rating = item.rating
    }
}