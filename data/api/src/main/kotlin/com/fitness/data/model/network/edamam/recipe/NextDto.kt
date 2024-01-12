package com.fitness.data.model.network.edamam.recipe


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NextDto(
    val href: String?,
    val title: String?
)