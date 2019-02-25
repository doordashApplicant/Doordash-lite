package com.doordash.doordash_lite.infra

import com.doordash.doordash_lite.di.DependencyManager
import com.doordash.doordash_lite.models.RestaurantModel
import com.doordash.doordash_lite.source.DoordashService

class TestDependencyManager : DependencyManager {
	lateinit var generateResult: () -> List<RestaurantModel>

	override fun getDoordashService(): DoordashService {
		return object : DoordashService() {
			override fun getNearbyRestaurants(): List<RestaurantModel> {
				return generateResult()
			}
		}
	}

	enum class ResultState {
		NoResult,
		EmptyResult,
		FewResults
	}

	fun changeResultState(state: ResultState) {
		generateResult = when (state) {
			ResultState.NoResult -> {
				{
					Thread.sleep(50000)
					emptyList()
				}
			}
			ResultState.EmptyResult -> {
				{
					emptyList()
				}
			}
			ResultState.FewResults -> {
				{
					listOf(
						RestaurantModel(
							0,
							"Closed",
							"My restaurant",
							"A beautiful restaurant in downtown Burlingame",
							"https://image.freepik.com/free-icon/restaurant-cutlery-circular-interface-symbol-with-a-knife-and-a-spoon_318-61014.png",
							"500"
						)
					)
				}
			}
		}

	}
}