package com.fitness.onboard.onboard.basic.viewmodel

data class BasicInformationState(
    val age: Int = 0,
    val weight: Double = 200.0,
    val step: BasicInformationStep = BasicInformationStep.GENDER_AGE
)

sealed class BasicInformationEvent{
    data class GenderAge(val gender: String, val age: Int): BasicInformationEvent()
    data class HeightWeight(val height: Double, val weight: Double): BasicInformationEvent()
    data class HealthConcerns(val dietaryPreferences: List<String>, val healthConcerns: List<String>)
}

enum class BasicInformationStep {
    GENDER_AGE,
    HEIGHT_WEIGHT,
    HEALTH_CONCERNS
}