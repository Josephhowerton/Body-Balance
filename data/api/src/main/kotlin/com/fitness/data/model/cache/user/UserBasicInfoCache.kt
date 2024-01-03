package com.fitness.data.model.cache.user

import enums.EGender

data class UserBasicInfoCache(
    val userId: String,
    val age: Int,
    val gender: EGender,
    val height: Double,
    val weight: Double,
    val lastUpdated: Long,
)