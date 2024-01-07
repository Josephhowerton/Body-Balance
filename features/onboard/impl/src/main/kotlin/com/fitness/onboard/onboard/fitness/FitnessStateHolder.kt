package com.fitness.onboard.onboard.fitness

import com.fitness.onboard.onboard.fitness.viewmodel.FitnessStep
import enums.EFitnessHabits
import enums.EFitnessLevel

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
    val currentStep: FitnessStep = FitnessStep.FITNESS_LEVELS,
    val level: EFitnessLevel? = null,
    val habits: List<EFitnessHabits> = emptyList()
)
