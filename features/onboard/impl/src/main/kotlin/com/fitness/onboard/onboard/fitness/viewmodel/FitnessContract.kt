package com.fitness.onboard.onboard.fitness.viewmodel

import enums.EFitnessInterest
import enums.EPhysicalActivityLevel

data class FitnessState(val step: FitnessStep = FitnessStep.FITNESS_LEVELS, val habits: List<EFitnessInterest> = emptyList())


sealed class FitnessEvent{
    data class FitnessLevel(val level: EPhysicalActivityLevel): FitnessEvent()
    data class Habits(val habits: List<EFitnessInterest>): FitnessEvent()
    object DismissDialog: FitnessEvent()
    object SaveFitnessInfo: FitnessEvent()
}

enum class FitnessStep {
    FITNESS_LEVELS,
    HABITS,
    SAVE_FITNESS_INFO,
    COMPLETE
}