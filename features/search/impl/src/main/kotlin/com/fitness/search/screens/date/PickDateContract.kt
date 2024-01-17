package com.fitness.search.screens.date

import com.fitness.search.SearchRecipeStep

data class PickDateState(val step: SearchRecipeStep = SearchRecipeStep.PENDING)

sealed class PickDateEvent{
    data class DateSelected(val date: Long): PickDateEvent()
}