package com.fitness.data.model.cache.user

import enums.EFitnessHabits
import enums.EFitnessLevel
data class UserBasicFitnessLevelCache(
    val userId: String,
    val level: EFitnessLevel,
    val habits: List<EFitnessHabits>,
    val lastUpdated: Long,
)
