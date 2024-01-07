package com.fitness.data.model.network.edamam.food

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodResponse(
    @Json(name = "_links") val pagination: LinksDto,
    @Json(name = "hints") val foodData: List<FoodDataDto>,
    @Json(name = "parsed") val matchedFood: List<MatchedFoodDto>,
    @Json(name = "text") val search: String
)