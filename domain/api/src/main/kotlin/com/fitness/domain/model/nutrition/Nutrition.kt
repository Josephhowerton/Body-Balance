package com.fitness.domain.model.nutrition

import cache.generateUniqueId
import enums.EMealType

data class Nutrition(
    val userId: String,
    val dateTime: String,
    val mealType: EMealType,
    val recipe: Recipe,
    val recordId: String = generateUniqueId("$userId-$dateTime-${recipe.recipeId}"),
)