package com.example.restaurantapp.repository

import com.example.restaurantapp.application.Application
import com.example.restaurantapp.constants.Constants
import com.example.restaurantapp.domain.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.RoundingMode
import java.text.DecimalFormat

class Repository {

    private val df = DecimalFormat("#.#")

    private val moshi by lazy {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    private fun fetchRestaurantList(): List<RestaurantsItem> {
        return moshi.adapter(RestaurantsListResponse::class.java)
            .fromJson(getJsonDataFromAsset(Constants.restaurantsListFileName))!!.restaurants
    }

    private fun fetchMenuList(): List<MenusItem> {
        return moshi.adapter(MenuListResponse::class.java)
            .fromJson(getJsonDataFromAsset(Constants.menuListFileName))!!.menus
    }

    private fun getJsonDataFromAsset(fileName: String): String {
        return Application.getAppContext()!!.assets.open(fileName).bufferedReader()
            .use { it.readText() }
    }

    suspend fun fetchRestaurantMainList(): ArrayList<RestaurantMainItem> {
        val mainList = ArrayList<RestaurantMainItem>()
        var foodItems = ""
        var rating = 0.0f
        df.roundingMode = RoundingMode.CEILING

        withContext(Dispatchers.Default) {

            fetchRestaurantList().forEach { restaurantsItem ->
                fetchMenuList().filter { it.restaurantId == restaurantsItem.id }[0].categories.forEach { categoriesItem ->
                    categoriesItem.menuItems.forEach { menuItem ->
                        foodItems = foodItems + menuItem.name + ", "
                    }
                }
                foodItems = foodItems.substringBeforeLast(",")

                restaurantsItem.reviews.forEach { reviewsItem ->
                    rating = rating.plus(reviewsItem.rating)
                }
                rating = rating.div(restaurantsItem.reviews.size)
                rating = df.format(rating).toFloat()

                mainList.add(
                    RestaurantMainItem(
                        restaurantsItem.id,
                        restaurantsItem.name,
                        restaurantsItem.neighborhood,
                        foodItems,
                        rating,
                        restaurantsItem.cuisineType
                    )
                )
                rating = 0.0f
                foodItems = ""
            }
        }
        return mainList
    }
}