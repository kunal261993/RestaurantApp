package com.example.restaurantapp.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurantsListResponse(

    @Json(name = "restaurants")
    val restaurants: List<RestaurantsItem>
) : Parcelable

@Parcelize
data class Latlng(

    @Json(name = "lng")
    val lng: Double? = null,

    @Json(name = "lat")
    val lat: Double? = null
) : Parcelable

@Parcelize
data class RestaurantsItem(

    @Json(name = "photograph")
    val photograph: String? = null,

    @Json(name = "address")
    val address: String? = null,

    @Json(name = "reviews")
    val reviews: List<ReviewsItem>,

    @Json(name = "operating_hours")
    val operatingHours: OperatingHours? = null,

    @Json(name = "name")
    val name: String = "",

    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "neighborhood")
    val neighborhood: String = "",

    @Json(name = "cuisine_type")
    val cuisineType: String = "",

    @Json(name = "latlng")
    val latlng: Latlng? = null
) : Parcelable

@Parcelize
data class OperatingHours(

    @Json(name = "Monday")
    val monday: String? = null,

    @Json(name = "Thursday")
    val thursday: String? = null,

    @Json(name = "Friday")
    val friday: String? = null,

    @Json(name = "Sunday")
    val sunday: String? = null,

    @Json(name = "Wednesday")
    val wednesday: String? = null,

    @Json(name = "Tuesday")
    val tuesday: String? = null,

    @Json(name = "Saturday")
    val saturday: String? = null
) : Parcelable

@Parcelize
data class ReviewsItem(

    @Json(name = "date")
    val date: String? = null,

    @Json(name = "comments")
    val comments: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "rating")
    val rating: Int = 0
) : Parcelable
