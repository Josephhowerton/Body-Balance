package com.fitness.data.model.domain.user

import enums.FitnessHabits
import enums.FitnessLevel

data class UserFitnessLevelDomain(
    val userId: String,
    val level: FitnessLevel,
    val habits: List<FitnessHabits>
)