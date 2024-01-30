package com.fitness.domain.model.user

import enums.EFitnessInterest
import enums.EPhysicalActivityLevel

data class UserFitnessLevel(
    val userId: String,
    val level: EPhysicalActivityLevel,
    val habits: List<EFitnessInterest>
)