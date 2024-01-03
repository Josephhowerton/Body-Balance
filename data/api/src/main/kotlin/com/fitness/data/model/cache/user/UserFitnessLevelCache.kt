package com.fitness.data.model.cache.user

import enums.FitnessHabits
import enums.FitnessLevel
data class UserFitnessLevelCache(
    val userId: String,
    val level: FitnessLevel,
    val habits: List<FitnessHabits>
)
