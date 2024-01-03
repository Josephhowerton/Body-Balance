package com.fitness.data.model.domain.user

import enums.Gender

data class UserBasicInfoDomain(
    val userId: String,
    val age: Int,
    val gender: Gender,
    val height: Double,
    val weight: Double
)