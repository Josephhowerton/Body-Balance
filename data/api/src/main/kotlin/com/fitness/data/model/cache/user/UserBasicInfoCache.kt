package com.fitness.data.model.cache.user

import enums.Gender

data class UserBasicInfoCache(
    val userId: String,
    val age: Int,
    val gender: Gender,
    val height: Double,
    val weight: Double
)