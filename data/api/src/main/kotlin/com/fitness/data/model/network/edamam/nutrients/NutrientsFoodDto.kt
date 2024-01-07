package com.fitness.data.model.network.edamam.nutrients

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class NutrientsFoodDto(
    @Json(name = "food") val food: String,
    @Json(name = "foodId") val foodId: String,
    @Json(name = "measure") val measure: String,
    @Json(name = "measureURI") val measureURI: String,
    @Json(name = "quantity") val quantity: Int,
    @Json(name = "retainedWeight") val retainedWeight: Int,
    @Json(name = "status") val status: String,
    @Json(name = "weight") val weight: Int
) : Parcelable