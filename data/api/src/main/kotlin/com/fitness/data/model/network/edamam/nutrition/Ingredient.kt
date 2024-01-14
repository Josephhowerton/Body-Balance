package com.fitness.data.model.network.edamam.nutrition


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ingredient(
    val parsed: List<Parsed?>? = null
)