package com.fitness.data.model.cache.user

import enums.EFitnessInterest
import enums.EPhysicalActivityLevel
data class UserBasicFitnessLevelCache(
    val userId: String,
    val level: EPhysicalActivityLevel,
    val habits: List<EFitnessInterest>,
    val lastUpdated: Long,
)
