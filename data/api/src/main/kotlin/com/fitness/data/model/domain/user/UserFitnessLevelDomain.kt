package com.fitness.data.model.domain.user

import enums.EFitnessHabits
import enums.EFitnessLevel

data class UserFitnessLevelDomain(
    val userId: String,
    val level: EFitnessLevel,
    val habits: List<EFitnessHabits>
)