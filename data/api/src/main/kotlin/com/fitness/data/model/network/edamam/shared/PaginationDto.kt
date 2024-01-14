package com.fitness.data.model.network.edamam.shared


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationDto(
    val next: NextDto?
)