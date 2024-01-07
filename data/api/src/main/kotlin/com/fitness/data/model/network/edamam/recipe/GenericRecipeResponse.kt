package com.fitness.data.model.network.edamam.recipe

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenericRecipeResponse(
    @Json(name = "_links") val links: PaginationDto,
    @Json(name = "count") val count: Int,
    @Json(name = "from") val from: Int,
    @Json(name = "hits") val hits: List<HitDto>,
    @Json(name = "to") val to: Int
)