package com.fitness.data.model.network.edamam.params
data class FoodSearchIngredientParams(
    val ingredient: String,
    val health: String? = null,
    val calories: String? = null,
    val category: String? = null,
)

data class FoodSearchBrandParams(
    val brand: String,
    val health: String? = null,
    val calories: String? = null,
    val category: String? = null,
)