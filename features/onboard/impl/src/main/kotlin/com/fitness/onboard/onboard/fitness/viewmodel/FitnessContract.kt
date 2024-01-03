package com.fitness.onboard.onboard.fitness.viewmodel

import enums.EFitnessHabits

data class FitnessState(val step: FitnessStep = FitnessStep.FITNESS_LEVELS)


sealed class FitnessEvent{
    data class FitnessLevel(val level: enums.EFitnessLevel): FitnessEvent()
    data class Habits(val habits: List<EFitnessHabits>): FitnessEvent()
    object SaveFitnessInfo: FitnessEvent()
}

enum class FitnessStep {
    FITNESS_LEVELS,
    HABITS,
    SAVE_FITNESS_INFO,
    COMPLETE
}