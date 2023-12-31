package com.fitness.onboard.onboard.basic.viewmodel

data class BasicInformationState(val step: BasicInformationStep = BasicInformationStep.GENDER_AGE)

sealed class BasicInformationEvent{
    data class GenderAge(val gender: String, val age: Int): BasicInformationEvent()
    data class Height(val height: Double, val weight: Double): BasicInformationEvent()
    data class Weight(val weight: Double): BasicInformationEvent()
    data class HealthConcerns(val healthConcerns: List<String>): BasicInformationEvent()
    data class DietaryPreferences(val dietaryPreferences: List<String>): BasicInformationEvent()
    object SaveBasicInformation: BasicInformationEvent()
}

enum class BasicInformationStep {
    GENDER_AGE,
    HEIGHT,
    WEIGHT,
    HEALTH_CONCERNS,
    DIETARY_PREFERENCES,
    SAVE_BASIC_INFORMATION,
    COMPLETE
}