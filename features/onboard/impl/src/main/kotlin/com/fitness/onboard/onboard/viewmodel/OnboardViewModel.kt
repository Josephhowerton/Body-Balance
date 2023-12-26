package com.fitness.onboard.onboard.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor() : IntentViewModel<BaseViewState<OnboardState>, OnboardEvent>() {

    init {
        setState(BaseViewState.Data(OnboardState()))
    }
    override fun onTriggerEvent(event: OnboardEvent) {}

}