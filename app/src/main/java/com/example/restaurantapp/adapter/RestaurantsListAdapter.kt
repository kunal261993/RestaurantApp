package com.example.restaurantapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.databinding.RestaurantItemBinding
import com.example.restaurantapp.domain.RestaurantMainItem

class RestaurantsListAdapter(val clickListener: RestaurantsListListener) :
    ListAdapter<RestaurantMainItem,
            RestaurantsListAdapter.ViewHolder>(RestaurantsListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: RestaurantsListListener, item: RestaurantMainItem) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RestaurantItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class RestaurantsListDiffCallback : DiffUtil.ItemCallback<RestaurantMainItem>() {
    override fun areItemsTheSame(
        oldItem: RestaurantMainItem,
        newItem: RestaurantMainItem
    ): Boolean {
        return oldItem.restaurantId == newItem.restaurantId
    }

    override fun areContentsTheSame(
        oldItem: RestaurantMainItem,
        newItem: RestaurantMainItem
    ): Boolean {
        return oldItem == newItem
    }
}

class RestaurantsListListener(val clickListener: (restaurantData: RestaurantMainItem) -> Unit) {
    fun onClick(restaurantData: RestaurantMainItem) = clickListener(restaurantData)
}