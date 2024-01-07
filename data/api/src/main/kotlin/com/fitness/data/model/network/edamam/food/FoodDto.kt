package com.fitness.data.model.network.edamam.food

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class FoodDto(
    @Json(name = "foodId") val foodId: String,
    @Json(name = "knownAs") val knownAs: String,
    @Json(name = "label") val label: String,
    @Json(name = "brand") val brand: String,
    @Json(name = "category") val category: String,
    @Json(name = "categoryLabel") val categoryLabel: String,
    @Json(name = "foodContentsLabel") val foodContentsLabel: String,
    @Json(name = "image") val image: String,
    @Json(name = "nutrients") val nutrients: NutrientsDto,
    @Json(name = "servingSizes") val servingSizes: List<ServingSizeDto>,
    @Json(name = "servingsPerContainer") val servingsPerContainer: Double,
) : Parcelable