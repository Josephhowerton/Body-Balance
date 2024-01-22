package com.fitness.recipebuilder.screens.confirmation

import com.fitness.recipebuilder.util.RecipeBuilderStep

data class SaveAndConfirmState(
    val date: Long? = null,
    val hour: Int? = null,
    val minute: Int? = null,
    val step: RecipeBuilderStep = RecipeBuilderStep.PENDING,
)

sealed class SaveAndConfirmEvent {
    object Save : SaveAndConfirmEvent()

}