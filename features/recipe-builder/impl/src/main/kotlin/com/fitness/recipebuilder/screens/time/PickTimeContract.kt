package com.fitness.recipebuilder.screens.time

import com.fitness.recipebuilder.util.RecipeBuilderStep

data class PickTimeState(val step: RecipeBuilderStep = RecipeBuilderStep.PENDING, )

sealed class PickTimeEvent{
    data class TimeSelected(val hour: Int, val minute: Int): PickTimeEvent()
}