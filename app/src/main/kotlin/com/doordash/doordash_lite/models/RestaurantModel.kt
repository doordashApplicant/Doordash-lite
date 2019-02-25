package com.doordash.doordash_lite.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurantModel(
	val id: Int,
	val status: String,
	val name: String,
	val description: String,
	@SerializedName("cover_img_url")
	val coverImage: String,
	@SerializedName("delivery_fee")
	val deliveryFee: String
) : Parcelable