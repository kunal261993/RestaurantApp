package com.example.restaurantapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.repository.Repository
import com.example.restaurantapp.domain.RestaurantMainItem
import kotlinx.coroutines.launch

class RestaurantsListFragmentViewModel : ViewModel() {

    private val repository = Repository()
    private var matchedRestaurantsData = ArrayList<RestaurantMainItem>()
    private var restaurantMainData = ArrayList<RestaurantMainItem>()

    private val _restaurantMainList = MutableLiveData<ArrayList<RestaurantMainItem>>()
    val restaurantMainList: LiveData<ArrayList<RestaurantMainItem>>
        get() = _restaurantMainList

    private val _noDataMatched = MutableLiveData<Boolean>()
    val noDataMatched: LiveData<Boolean>
        get() = _noDataMatched

    init {
        viewModelScope.launch {
            restaurantMainData = repository.fetchRestaurantMainList()
            _restaurantMainList.value = restaurantMainData
        }
    }

    private val _restaurantMainItem = MutableLiveData<RestaurantMainItem>()
    val restaurantMainItem: LiveData<RestaurantMainItem>
        get() = _restaurantMainItem

    fun onItemClicked(restaurantInfo: RestaurantMainItem) {
        _restaurantMainItem.value = restaurantInfo
    }

    fun search(text: String?) {
        matchedRestaurantsData = arrayListOf()
        if (!text.isNullOrEmpty()) {
            restaurantMainData.forEach { restaurantsItem ->
                if (restaurantsItem.restaurantName.contains(
                        text,
                        true
                    ) || restaurantsItem.foodItems.contains(text, true)
                    || restaurantsItem.neighborhood.contains(
                        text,
                        true
                    ) || restaurantsItem.cuisineType.contains(text, true)
                ) {
                    matchedRestaurantsData.add(restaurantsItem)
                }
            }
            if (matchedRestaurantsData.isEmpty()) {
                _noDataMatched.value = true
                _restaurantMainList.value = matchedRestaurantsData
            } else {
                _noDataMatched.value = false
                _restaurantMainList.value = matchedRestaurantsData
            }
        } else {
            _noDataMatched.value = false
            _restaurantMainList.value = restaurantMainData
        }
    }
}