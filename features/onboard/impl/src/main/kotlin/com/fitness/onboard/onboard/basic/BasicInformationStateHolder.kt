package com.fitness.onboard.onboard.basic

import enums.EGender
import enums.ELengthUnit
import enums.EMassUnit
import enums.SystemOfMeasurement

object BasicInformationStateHolder {
    private var BasicInformationState: BasicInformationState = BasicInformationState()

    fun getState() = BasicInformationState
    fun updateState(newState: BasicInformationState) {
        BasicInformationState = newState
    }

    fun clearState() {
        BasicInformationState = BasicInformationState()
    }
}

data class BasicInformationState(
    val age: Int? = null,
    val gender: EGender? = null,
    val height: Double? = null,
    val weight: Double? = null,
    val waist: Double? = null,
    val preferredMeasurement: SystemOfMeasurement = SystemOfMeasurement.METRIC
)
