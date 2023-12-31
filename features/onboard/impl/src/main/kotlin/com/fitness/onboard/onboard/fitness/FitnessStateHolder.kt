package com.fitness.onboard.onboard.fitness

object FitnessStateHolder {
    private var fitnessState: FitnessState = FitnessState()

    fun getState() = fitnessState
    fun updateState(newState: FitnessState) {
        fitnessState = newState
    }

    fun clearState() {
        fitnessState = FitnessState()
    }
}

data class FitnessState(
    val currentFitnessLevel: String? = null,
    val exerciseHabits: List<String>? = null,
    val fitnessGoals: List<String>? = null
)
