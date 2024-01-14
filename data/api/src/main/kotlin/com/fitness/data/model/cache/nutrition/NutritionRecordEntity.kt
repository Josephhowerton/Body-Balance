package com.fitness.data.model.cache.nutrition

import enums.EMealType

data class NutritionRecordEntity(
    val recordId: String,
    val userId: String,
    val date: String,
    val mealType: EMealType,
    val recipe: RecipeEntity,
)
