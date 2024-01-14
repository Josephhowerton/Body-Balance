package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NextDto(
    val href: String? = null,
    val title: String? = null
)