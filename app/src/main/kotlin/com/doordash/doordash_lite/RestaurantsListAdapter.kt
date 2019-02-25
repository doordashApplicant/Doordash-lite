package com.doordash.doordash_lite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doordash.doordash_lite.models.RestaurantModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.restaurant_item.view.*

class RestaurantsListAdapter(var items: List<RestaurantModel>) :
	RecyclerView.Adapter<RestaurantsListAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			LayoutInflater.from(parent.context).inflate(
				R.layout.restaurant_item,
				parent,
				false
			)
		)
	}

	override fun getItemCount() = items.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val restaurant = items[position]

		restaurant.populate(holder.itemView)
	}

	class ViewHolder(item: View) : RecyclerView.ViewHolder(item)
}

// this is extracted so that can be reused when/if we reuse
// the same layout and model in another feature of the app
fun RestaurantModel.populate(
	root: View
) {
	root.description.text = description
	root.status.text = status
	root.name.text = name
	Picasso.get().load(coverImage).into(root.coverImage)
}