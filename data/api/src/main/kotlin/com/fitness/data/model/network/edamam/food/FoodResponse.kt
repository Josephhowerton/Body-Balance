package com.fitness.data.model.network.edamam.food


import com.fitness.data.model.network.edamam.recipe.PaginationDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodResponse(
    val hints: List<FoodData?>? = null,
    @Json(name = "_links") val links: PaginationDto? = null,
)