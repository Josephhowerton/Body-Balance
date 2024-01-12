package com.fitness.data.model.network.edamam


import com.fitness.data.model.network.edamam.recipe.HitDto
import com.fitness.data.model.network.edamam.recipe.PaginationDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val count: Int?,
    val from: Int?,
    val hits: List<HitDto>?,
    @Json(name = "_links") val links: PaginationDto?,
    val to: Int?
)