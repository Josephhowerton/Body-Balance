package com.fitness.data.model.domain.user

import enums.EGoals

data class UserBasicGoalsInfoDomain(
    val userId: String,
    val goals: List<EGoals>,
)
