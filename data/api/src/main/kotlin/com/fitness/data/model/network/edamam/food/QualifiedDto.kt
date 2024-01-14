package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QualifiedDto(
    val qualifiers: List<QualifierDto?>? = null,
    val weight: Double? = null
)