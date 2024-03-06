package com.fitness.onboard.onboard.finalize

import com.fitness.domain.model.user.UserBasicInfo
import com.fitness.onboard.onboard.finalize.viewmodel.FinalizeStep
import enums.SystemOfMeasurement

object FinalizeStateHolder {
    private var finalizeHolderState: FinalizeHolderState = FinalizeHolderState()

    fun getState() = finalizeHolderState
    fun updateState(newState: FinalizeHolderState) {
        finalizeHolderState = newState
    }

    fun clearState() {
        finalizeHolderState = FinalizeHolderState()
    }
}

data class FinalizeHolderState(
    val currentStep: FinalizeStep = FinalizeStep.INITIALIZE,
    val unitSystem: SystemOfMeasurement? = null,
    val userBasicInfo: UserBasicInfo? = null
)
