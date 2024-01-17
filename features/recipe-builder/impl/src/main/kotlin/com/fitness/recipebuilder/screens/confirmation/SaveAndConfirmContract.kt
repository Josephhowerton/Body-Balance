package com.fitness.recipebuilder.screens.confirmation

import com.fitness.recipebuilder.util.RecipeBuilderStep

data class SaveAndConfirmState(
    val date: Long = 0,
    val hour: Int = 0,
    val minute: Int = 0,
    val step: RecipeBuilderStep = RecipeBuilderStep.PENDING,
)

sealed class SaveAndConfirmEvent {
    object Save : SaveAndConfirmEvent()

}