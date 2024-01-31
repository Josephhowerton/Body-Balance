package com.fitness.onboard.onboard.basic.viewmodel

import enums.EGender
import enums.SystemOfMeasurement

data class BasicInformationState(
    val units: SystemOfMeasurement = SystemOfMeasurement.METRIC,
    val step: BasicInformationStep = BasicInformationStep.GENDER_AGE
)

sealed class BasicInformationEvent{
    data class SystemOfMeasurement(val systemOfMeasurement: enums.SystemOfMeasurement): BasicInformationEvent()
    data class GenderAge(val gender: EGender, val age: Int): BasicInformationEvent()
    data class Height(val height: Double): BasicInformationEvent()
    data class Weight(val weight: Double): BasicInformationEvent()
    data class Waist(val waist: Double): BasicInformationEvent()
    object SaveBasicInformation: BasicInformationEvent()
}

enum class BasicInformationStep {
    SYSTEM_OF_MEASUREMENTS,
    GENDER_AGE,
    HEIGHT,
    WEIGHT,
    WAIST,
    SAVE_BASIC_INFORMATION,
    COMPLETE
}