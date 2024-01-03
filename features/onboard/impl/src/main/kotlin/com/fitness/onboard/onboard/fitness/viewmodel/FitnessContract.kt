package com.fitness.onboard.onboard.fitness.viewmodel

import enums.FitnessHabits

data class FitnessState(val step: FitnessStep = FitnessStep.FITNESS_LEVELS)


sealed class FitnessEvent{
    data class FitnessLevel(val level: enums.FitnessLevel): FitnessEvent()
    data class Habits(val habits: List<FitnessHabits>): FitnessEvent()
    object SaveFitnessInfo: FitnessEvent()
}

enum class FitnessStep {
    FITNESS_LEVELS,
    HABITS,
    SAVE_FITNESS_INFO,
    COMPLETE
}