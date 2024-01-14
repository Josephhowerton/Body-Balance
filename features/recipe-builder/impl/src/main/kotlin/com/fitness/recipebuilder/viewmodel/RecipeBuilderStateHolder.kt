package com.fitness.recipebuilder.viewmodel

import enums.EMealType

object RecipeBuilderStateHolder {
    private var state: BuilderState = BuilderState()
    fun state() = state
    fun updateState(newState: BuilderState) {
        state = newState
    }

    fun clearState() {
        state = BuilderState()
    }
}
data class BuilderState(
    val food: List<FoodData> = emptyList(),
    val date: Long? = null,
    val type: EMealType? = null,
    val isOpen: Boolean = food.isNotEmpty(),
    val step: RecipeBuilderStep = RecipeBuilderStep.PENDING
)

enum class RecipeBuilderStep {
    PENDING,
    OPEN,
    SELECT_DATE,
    SELECT_MEAL_TYPE,
    SAVE
}
