package com.fitness.onboard.onboard.finalize

import com.fitness.domain.model.user.UserBasicInfo
import com.fitness.onboard.onboard.finalize.viewmodel.FinalizeStep
import enums.SystemOfMeasurement

object FinalizeStateHolder {
    private var finalizeState: FinalizeState = FinalizeState()

    fun getState() = finalizeState
    fun updateState(newState: FinalizeState) {
        finalizeState = newState
    }

    fun clearState() {
        finalizeState = FinalizeState()
    }
}

data class FinalizeState(
    val currentStep: FinalizeStep = FinalizeStep.INITIALIZE,
    val unitSystem: SystemOfMeasurement? = null,
    val userBasicInfo: UserBasicInfo? = null
)
