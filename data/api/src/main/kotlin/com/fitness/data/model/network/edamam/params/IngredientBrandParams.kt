package com.fitness.data.model.network.edamam.params

data class IngredientBrandParams(
    val brand: String,
    val health: String? = null,
    val calories: String? = null,
    val category: String? = null,
)