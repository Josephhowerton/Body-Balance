package com.fitness.search.nutrition.viewmodel

import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

class NutritionSearchViewModel @Inject constructor(): IntentViewModel<BaseViewState<NutritionSearchState>, NutritionSearchEvent>() {

    override fun onTriggerEvent(event: NutritionSearchEvent) {
        TODO("Not yet implemented")
    }
}