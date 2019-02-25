package com.doordash.doordash_lite.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.doordash.doordash_lite.models.RestaurantModel
import com.doordash.doordash_lite.source.DoordashService
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
	@Rule
	@JvmField
	var instantTaskExecutorRule = InstantTaskExecutorRule()

	lateinit var viewModel: MainViewModel

	lateinit var doordashService: DoordashService

	val restaurantModel = RestaurantModel(
		0,
		"Open",
		"testName",
		"testDes",
		"",
		"100"
	)

	val restaurantModel2 = RestaurantModel(
		1,
		"Open",
		"testName1",
		"testDes1",
		"",
		"200"
	)

	@Before
	fun setUp() {
		doordashService = mock {
			on { getNearbyRestaurants() } doReturn listOf(restaurantModel)
		}

		viewModel = MainViewModel()
		viewModel.dependencyManager = mock {
			on { getDoordashService() } doReturn doordashService
		}

	}

	@Test
	fun `load should hit the service for the first time`() {
		runBlocking {
			viewModel.load()
		}

		verify(doordashService, times(1)).getNearbyRestaurants()
	}

	@Test
	fun `load should push the restaurant list to the observers`(){
		val observer = mock<Observer<List<RestaurantModel>>>()
		viewModel.restaurantList.observeForever(observer)

		runBlocking {
			viewModel.load()
		}

		verify(observer).onChanged(listOf(restaurantModel))
	}

	@Test
	fun `load should not hit the service for a repeated call`() {
		runBlocking {
			viewModel.load()
			viewModel.load()

			verify(doordashService, times(1)).getNearbyRestaurants()
		}
	}

	@Test
	fun `refresh should force the service call and get new values`() {
		val observer = mock<Observer<List<RestaurantModel>>>()
		viewModel.restaurantList.observeForever(observer)


		runBlocking {
			viewModel.load()

			stubbing(doordashService) {
				on { getNearbyRestaurants() } doReturn listOf(restaurantModel, restaurantModel2)
			}

			viewModel.refresh()

			verify(doordashService, times(2)).getNearbyRestaurants()
			verify(observer, times(1)).onChanged(listOf(restaurantModel))
			verify(observer, atMost(1)).onChanged(listOf(restaurantModel))
			verify(observer, times(1)).onChanged(listOf(restaurantModel, restaurantModel2))
		}
	}
}