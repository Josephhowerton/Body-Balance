package com.fitness.data.model.network.edamam.recipe

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class IngredientDto(
    @Json(name = "food") val food: String,
    @Json(name = "foodId") val foodId: String,
    @Json(name = "measure") val measure: String,
    @Json(name = "quantity") val quantity: Int,
    @Json(name = "text") val text: String,
    @Json(name = "weight") val weight: Int
) : Parcelable