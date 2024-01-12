package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServingSize(
    val label: String? = null,
    val quantity: Double? = null,
    val uri: String? = null
)