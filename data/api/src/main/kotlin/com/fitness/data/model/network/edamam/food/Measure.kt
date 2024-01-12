package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Measure(
    val label: String? = null,
    val uri: String? = null,
    val weight: Double? = null
)