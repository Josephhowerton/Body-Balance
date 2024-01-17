package com.fitness.recipebuilder.screens.time

import com.fitness.recipebuilder.util.RecipeBuilderStep
import com.fitness.recipebuilder.util.RecipeBuilderStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import failure.GenericFailure
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class PickTimeViewModel @Inject constructor(private val searchStateHolder: RecipeBuilderStateHolder, ) : IntentViewModel<BaseViewState<PickTimeState>, PickTimeEvent>() {

    init { initialize() }

    override fun onTriggerEvent(event: PickTimeEvent) {
        if(event is PickTimeEvent.TimeSelected){
            onTimeSelected(event)
        }else{
            handleError(GenericFailure())
        }
    }

    private fun initialize() = setState(BaseViewState.Data(PickTimeState()))

    private fun onTimeSelected(event: PickTimeEvent.TimeSelected) = safeLaunch {
        val state = searchStateHolder.state().copy(hour = event.hour, minute = event.minute)
        searchStateHolder.updateState(state)
        setState(BaseViewState.Data(PickTimeState(step = RecipeBuilderStep.COMPLETE,)))
    }
}