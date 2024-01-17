package com.fitness.recipebuilder.screens.date

import com.fitness.recipebuilder.util.RecipeBuilderStep
import com.fitness.recipebuilder.util.RecipeBuilderStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import failure.GenericFailure
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class PickDateViewModel @Inject constructor(private val searchStateHolder: RecipeBuilderStateHolder, ) : IntentViewModel<BaseViewState<PickDateState>, PickDateEvent>() {

    init { initialize() }

    override fun onTriggerEvent(event: PickDateEvent) {
        if(event is PickDateEvent.DateSelected){
            onDateSelected(event)
        }else{
            handleError(GenericFailure())
        }
    }

    private fun initialize() = setState(BaseViewState.Data(PickDateState()))

    private fun onDateSelected(event: PickDateEvent.DateSelected) = safeLaunch {
        val state = searchStateHolder.state().copy(date = event.date)
        searchStateHolder.updateState(state)
        setState(
            BaseViewState.Data(
                PickDateState(step = RecipeBuilderStep.COMPLETE)
            )
        )
    }
}