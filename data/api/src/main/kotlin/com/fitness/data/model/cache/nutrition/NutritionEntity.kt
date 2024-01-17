package com.fitness.data.model.cache.nutrition

import enums.EMealType

data class NutritionEntity(
    val recordId: String,
    val userId: String,
    val dateTime: String,
    val mealType: EMealType,
    val recipe: RecipeEntity,
)
