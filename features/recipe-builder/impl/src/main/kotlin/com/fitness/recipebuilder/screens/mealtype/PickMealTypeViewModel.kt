package com.fitness.recipebuilder.screens.mealtype

import com.fitness.recipebuilder.util.RecipeBuilderStep
import com.fitness.recipebuilder.util.RecipeBuilderStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import failure.GenericFailure
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class PickMealTypeViewModel @Inject constructor(private val stateHolder: RecipeBuilderStateHolder) : IntentViewModel<BaseViewState<PickMealTypeState>, PickMealTypeEvent>() {

    init { initialize() }

    override fun onTriggerEvent(event: PickMealTypeEvent) {
        if(event is PickMealTypeEvent.MealTypeSelected){
            onTimeSelected(event)
        }else{
            handleError(GenericFailure())
        }
    }

    private fun initialize() = setState(BaseViewState.Data(PickMealTypeState()))

    private fun onTimeSelected(event: PickMealTypeEvent.MealTypeSelected) = safeLaunch {
        val state = stateHolder.state().copy(type = event.type)
        stateHolder.updateState(state)
        setState(BaseViewState.Data(PickMealTypeState(step = RecipeBuilderStep.COMPLETE)))
    }
}