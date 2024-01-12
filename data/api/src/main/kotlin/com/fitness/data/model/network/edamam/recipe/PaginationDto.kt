package com.fitness.data.model.network.edamam.recipe


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationDto(
    val next: NextDto?
)