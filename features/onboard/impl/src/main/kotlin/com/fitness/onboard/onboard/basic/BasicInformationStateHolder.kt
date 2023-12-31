package com.fitness.onboard.onboard.basic

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
    val gender: String? = null,
    val height: Double? = null,
    val weight: Double? = null,
    val dietaryPreferences: List<String>? = null,
    val healthConcerns: List<String>? = null
)
