package com.fitness.data.model.cache.edamam.recipe

data class IngredientEntity(
    val food: String,
    val foodId: String,
    val measure: String,
    val quantity: Int,
    val text: String,
    val weight: Int
)