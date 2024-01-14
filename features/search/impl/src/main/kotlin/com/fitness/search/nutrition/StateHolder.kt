package com.fitness.search.nutrition

import enums.EMealType

object SearchStateHolder {
    private var state: NutritionState = NutritionState()
    fun state() = state
    fun updateState(newState: NutritionState) {
        state = newState
    }

    fun clearNutritionState() {
        state = NutritionState()
    }
}
data class NutritionState(
    val recipes: List<Recipe> = emptyList(),
    val food: List<FoodData> = emptyList(),
    val recipe: Recipe? = null,
    val date: Long? = null,
    val type: EMealType? = null,
    val step: RecipeStep = RecipeStep.PENDING
)

enum class RecipeStep {
    PENDING,
    SELECT_DATE,
    SELECT_MEAL_TYPE,
    SAVE
}

