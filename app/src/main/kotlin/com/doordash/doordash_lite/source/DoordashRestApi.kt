package com.doordash.doordash_lite.source

import com.doordash.doordash_lite.models.RestaurantModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DoordashRestApi {
	@GET("v2/restaurant/")
	fun search(
		@Query("lat")
		lat: Double,
		@Query("lng")
		lng: Double,
		@Query("offset")
		offset: Int,
		@Query("limit")
		limit: Int
	): Call<List<RestaurantModel>>

	@GET("/v2/restaurant/{id}")
	fun getRestaurant(id: Int): Call<RestaurantModel>
}