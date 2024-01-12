package com.fitness.domain.model.edamam

data class Ingredient(
    val food: String? = null,
    val foodCategory: String? = null,
    val foodId: String? = null,
    val image: String? = null,
    val measure: String? = null,
    val quantity: Double? = null,
    val text: String? = null,
    val weight: Double? = null
)