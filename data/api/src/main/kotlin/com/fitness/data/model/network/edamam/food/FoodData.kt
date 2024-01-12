package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodData(
    val food: Food? = null,
    val measures: List<Measure?>? = null
)