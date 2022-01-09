package com.example.restaurantapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.restaurantapp.adapter.RestaurantsListAdapter
import com.example.restaurantapp.adapter.RestaurantsListListener
import com.example.restaurantapp.databinding.RestaurantsListBinding
import com.example.restaurantapp.viewmodel.RestaurantsListFragmentViewModel

class RestaurantsListFragment : Fragment() {

    private val viewModel: RestaurantsListFragmentViewModel by activityViewModels()
    private lateinit var binding: RestaurantsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RestaurantsListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val adapter = RestaurantsListAdapter(RestaurantsListListener { restaurantData ->
            viewModel.onItemClicked(restaurantData)
            this.findNavController()
                .navigate(RestaurantsListFragmentDirections.actionRestaurantsListFragmentToRestaurantDetailsFragment())
        })

        binding.recyclerView.adapter = adapter

        viewModel.restaurantMainList.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it.toMutableList())
            }
        })

        viewModel.noDataMatched.observe(viewLifecycleOwner, {
            it?.let {
                binding.noMatchFoundTextView.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
        })

        performSearch()
        return binding.root
    }

    private fun performSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText)
                return true
            }
        })
    }

}