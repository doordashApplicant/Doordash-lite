package com.doordash.doordash_lite.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.doordash.doordash_lite.DoordashApp
import com.doordash.doordash_lite.R
import com.doordash.doordash_lite.RestaurantsListAdapter
import com.doordash.doordash_lite.models.RestaurantModel
import com.doordash.doordash_lite.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

	private val dependencyManager by lazy { (application as DoordashApp).getDependencymanager() }
	lateinit var viewModel: MainViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
		viewModel.dependencyManager = dependencyManager

		setupLayout()

		GlobalScope.launch(Dispatchers.Main) {
			viewModel.load()
		}
	}

	private fun setupLayout() {
		loadingIndicator.visibility = View.VISIBLE

		discoveryRecyclerView.apply {
			val linearLayoutManager = LinearLayoutManager(this@MainActivity)
			layoutManager = linearLayoutManager
			adapter = RestaurantsListAdapter(emptyList())
			val dividerItemDecoration = DividerItemDecoration(
				this@MainActivity, linearLayoutManager.orientation
			)
			addItemDecoration(dividerItemDecoration)
		}

		pullToRefresh.setOnRefreshListener {
			GlobalScope.launch(Dispatchers.Main) {
				viewModel.refresh()
			}
		}

		pullToRefresh.setColorSchemeColors(resources.getColor(android.R.color.holo_red_dark, null))
	}

	override fun onStart() {
		super.onStart()

		viewModel.restaurantList.observe(this,
			Observer<List<RestaurantModel>> { t ->
				loadingIndicator.visibility = View.GONE
				if (pullToRefresh.isRefreshing) {
					pullToRefresh.isRefreshing = false
				}
				discoveryRecyclerView.swapAdapter(
					RestaurantsListAdapter(t), true
				)
			})
	}
}