package com.fitness.data.model.network.edamam


import com.fitness.data.model.network.edamam.food.FoodDataDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodResponse(
    val hints: List<FoodDataDto>? = listOf(),
    @Json(name = "_links") val links: Pagination? = null,
)