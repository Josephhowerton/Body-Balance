package com.fitness.data.model.network.edamam.recipe

import com.fitness.data.model.network.edamam.recipe.HitDto
import com.fitness.data.model.network.edamam.recipe.PaginationDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UriRecipeResponse(
    @Json(name = "_links") val pagination: PaginationDto,
    @Json(name = "count") val count: Int,
    @Json(name = "from") val from: Int,
    @Json(name = "hits") val hits: List<HitDto>,
    @Json(name = "to") val to: Int
)