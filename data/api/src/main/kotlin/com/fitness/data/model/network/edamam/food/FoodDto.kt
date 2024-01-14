package com.fitness.data.model.network.edamam.food


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodDto(
    val category: String? = null,
    val categoryLabel: String? = null,
    val foodId: String? = null,
    val image: String? = null,
    val knownAs: String? = null,
    val label: String? = null,
    val nutrients: NutrientsDto? = null
)