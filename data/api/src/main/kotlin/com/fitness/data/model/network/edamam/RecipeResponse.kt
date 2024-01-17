package com.fitness.data.model.network.edamam


import com.fitness.data.model.network.edamam.recipe.HitDto
import com.fitness.data.model.network.edamam.shared.PaginationDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val hits: List<HitDto>?,
    @Json(name = "_links") val links: PaginationDto?
)