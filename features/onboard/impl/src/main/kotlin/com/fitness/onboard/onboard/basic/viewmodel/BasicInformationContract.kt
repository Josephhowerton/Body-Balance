package com.fitness.onboard.onboard.basic.viewmodel

import enums.Gender
import enums.SystemOfMeasurement

data class BasicInformationState(
    val units: SystemOfMeasurement = SystemOfMeasurement.CUSTOMARY,
    val step: BasicInformationStep = BasicInformationStep.GENDER_AGE
)

sealed class BasicInformationEvent{
    data class GenderAge(val gender: Gender, val age: Int): BasicInformationEvent()
    data class Height(val height: Double): BasicInformationEvent()
    data class Weight(val weight: Double): BasicInformationEvent()
    object SaveBasicInformation: BasicInformationEvent()
}

enum class BasicInformationStep {
    GENDER_AGE,
    HEIGHT,
    WEIGHT,
    SAVE_BASIC_INFORMATION,
    COMPLETE
}