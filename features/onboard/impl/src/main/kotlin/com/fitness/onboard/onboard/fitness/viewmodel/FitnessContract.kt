package com.fitness.onboard.onboard.fitness.viewmodel

data class FitnessState(val step: FitnessStep = FitnessStep.FITNESS_LEVELS)


sealed class FitnessEvent{
    data class FitnessLevels(val currentFitnessLevel: String): FitnessEvent()
    data class Habits(val exerciseHabits: List<String>): FitnessEvent()
    data class Goals(val fitnessGoals: List<String>): FitnessEvent()
    object SaveFitnessInfo: FitnessEvent()
}

enum class FitnessStep {
    FITNESS_LEVELS,
    HABITS,
    GOALS,
    SAVE_FITNESS_INFO,
    COMPLETE
}