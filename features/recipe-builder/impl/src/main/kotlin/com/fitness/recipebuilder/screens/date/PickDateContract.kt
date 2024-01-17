package com.fitness.recipebuilder.screens.date

import com.fitness.recipebuilder.util.RecipeBuilderStep

data class PickDateState(val step: RecipeBuilderStep = RecipeBuilderStep.PENDING)

sealed class PickDateEvent{
    data class DateSelected(val date: Long): PickDateEvent()
}