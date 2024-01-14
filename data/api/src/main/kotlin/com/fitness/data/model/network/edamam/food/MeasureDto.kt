package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MeasureDto(
    val label: String? = null,
    val qualified: List<QualifiedDto?>? = null,
    val uri: String? = null,
    val weight: Double? = null
)