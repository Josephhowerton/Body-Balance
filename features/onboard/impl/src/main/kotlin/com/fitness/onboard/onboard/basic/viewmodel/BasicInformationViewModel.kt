package com.fitness.onboard.onboard.basic.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class BasicInformationViewModel @Inject constructor(): IntentViewModel<BaseViewState<BasicInformationState>, BasicInformationEvent>() {
    override fun onTriggerEvent(event: BasicInformationEvent) {

    }
}