package com.fitness.domain.model.user

import enums.EFitnessHabits
import enums.EFitnessLevel

data class UserFitnessLevel(
    val userId: String,
    val level: EFitnessLevel,
    val habits: List<EFitnessHabits>
)