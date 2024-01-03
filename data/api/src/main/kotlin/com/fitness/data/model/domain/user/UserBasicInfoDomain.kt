package com.fitness.data.model.domain.user

import enums.EGender

data class UserBasicInfoDomain(
    val userId: String,
    val age: Int,
    val gender: EGender,
    val height: Double,
    val weight: Double
)