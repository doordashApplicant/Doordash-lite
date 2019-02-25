package com.doordash.doordash_lite.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doordash.doordash_lite.di.DependencyManager
import com.doordash.doordash_lite.models.RestaurantModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
	lateinit var dependencyManager: DependencyManager

	private val doordashService by lazy {
		dependencyManager.getDoordashService()
	}

	private val mutableRestaurantList = MutableLiveData<List<RestaurantModel>>()
	val restaurantList: LiveData<List<RestaurantModel>>
		get() = mutableRestaurantList

	suspend fun load(force: Boolean = false) {
		if (mutableRestaurantList.value == null || force) {
			val restaurants = withContext(Dispatchers.IO) {
				doordashService.getNearbyRestaurants()
			}

			mutableRestaurantList.postValue(restaurants)
		}
	}

	suspend fun refresh() = load(force = true)
}