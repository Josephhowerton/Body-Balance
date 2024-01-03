package com.fitness.data.model.cache.user

import enums.EGoals

data class UserBasicGoalsInfoCache(
    val userId: String,
    val goals: List<EGoals>
)