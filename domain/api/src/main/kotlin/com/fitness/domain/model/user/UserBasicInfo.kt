package com.fitness.domain.model.user

import enums.EGender

data class UserBasicInfo(
    val userId: String,
    val age: Int,
    val gender: EGender,
    val height: Double,
    val weight: Double
)