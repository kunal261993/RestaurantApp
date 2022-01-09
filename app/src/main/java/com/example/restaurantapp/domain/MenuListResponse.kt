package com.example.restaurantapp.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuListResponse(

    @Json(name = "menus")
    val menus: List<MenusItem>
) : Parcelable

@Parcelize
data class MenuItemsItem(

    @Json(name = "images")
    val images: List<String?>? = null,

    @Json(name = "price")
    val price: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "id")
    val id: String? = null
) : Parcelable

@Parcelize
data class CategoriesItem(

    @Json(name = "menu-items")
    val menuItems: List<MenuItemsItem>,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "id")
    val id: String? = null
) : Parcelable

@Parcelize
data class MenusItem(

    @Json(name = "categories")
    val categories: List<CategoriesItem>,

    @Json(name = "restaurantId")
    val restaurantId: Int = 0
) : Parcelable
