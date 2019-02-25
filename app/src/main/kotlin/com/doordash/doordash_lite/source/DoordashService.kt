package com.doordash.doordash_lite.source

import androidx.annotation.WorkerThread
import com.doordash.doordash_lite.models.RestaurantModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val DOORDASH_API_URL = "https://api.doordash.com"

open class DoordashService {
	private val doordashRetrofit by lazy {
		val okHttpClient = OkHttpClient.Builder().also {
			it.addNetworkInterceptor(
				HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).setLevel(
					// this is useful for debugging but for production builds should be set to none
					HttpLoggingInterceptor.Level.NONE
				)
			)
		}.build()

		return@lazy Retrofit.Builder()
			.baseUrl(DOORDASH_API_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient)
			.build()
	}

	private val api: DoordashRestApi = doordashRetrofit.create(DoordashRestApi::class.java)

	@WorkerThread
	open fun getNearbyRestaurants(): List<RestaurantModel>? {
		// If I had some more time I would have made the limit smaller and implement pagination so that the initial load is faster
		val response = api.search(37.422740, -122.139956, 0, 50).execute()
		return response.body()
	}
}

