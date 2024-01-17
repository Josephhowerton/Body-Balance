package com.fitness.recipebuilder.screens.mealtype

import com.fitness.recipebuilder.util.RecipeBuilderStep
import enums.EMealType

data class PickMealTypeState(val step: RecipeBuilderStep = RecipeBuilderStep.PENDING)

sealed class PickMealTypeEvent{
    data class MealTypeSelected(val type: EMealType): PickMealTypeEvent()
}