package com.fitness.domain.model.nutrition

import enums.EMealType

data class NutritionRecord(
    val recordId: String,
    val userId: String,
    val date: String,
    val mealType: EMealType,
    val recipe: Recipe,
)
