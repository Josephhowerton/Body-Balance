package com.fitness.search.screens.confirmation

import com.fitness.search.SearchRecipeStep

data class SaveAndConfirmState(
    val date: Long = 0,
    val hour: Int = 0,
    val minute: Int = 0,
    val step: SearchRecipeStep = SearchRecipeStep.PENDING,
)

sealed class SaveAndConfirmEvent {
    object Save : SaveAndConfirmEvent()

}