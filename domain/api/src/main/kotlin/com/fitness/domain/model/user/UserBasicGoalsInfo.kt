package com.fitness.domain.model.user

import enums.EGoals

data class UserBasicGoalsInfo(
    val userId: String,
    val goals: List<EGoals>,
)
