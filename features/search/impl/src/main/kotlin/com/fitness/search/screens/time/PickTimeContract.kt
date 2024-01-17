package com.fitness.search.screens.time

import com.fitness.search.SearchRecipeStep
import com.fitness.search.screens.date.PickDateEvent

data class PickTimeState(val step: SearchRecipeStep = SearchRecipeStep.PENDING, )

sealed class PickTimeEvent{
    data class TimeSelected(val hour: Int, val minute: Int): PickTimeEvent()
}