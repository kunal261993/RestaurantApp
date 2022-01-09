package com.example.restaurantapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.restaurantapp.databinding.RestaurantDetailsBinding
import com.example.restaurantapp.viewmodel.RestaurantsListFragmentViewModel

class RestaurantDetailsFragment : Fragment() {
    private val viewModel: RestaurantsListFragmentViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RestaurantDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.item = viewModel
        return binding.root
    }
}