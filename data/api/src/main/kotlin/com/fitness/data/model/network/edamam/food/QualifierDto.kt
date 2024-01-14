package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QualifierDto(
    val label: String? = null,
    val uri: String? = null
)