package com.fitness.data.model.network.edamam.recipe

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    @Json(name = "_links") val pagination: PaginationDto,
    @Json(name = "recipe") val recipe: RecipeDto
)