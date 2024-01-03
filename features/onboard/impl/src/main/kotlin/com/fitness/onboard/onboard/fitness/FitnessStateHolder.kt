package com.fitness.onboard.onboard.fitness

import enums.FitnessHabits
import enums.FitnessLevel

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
    val level: FitnessLevel? = null,
    val habits: List<FitnessHabits>? = null
)
