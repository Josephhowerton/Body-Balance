package com.fitness.data.model.model.user

data class UserBasicInfoDomain(
    val userId: String,
    val age: Int,
    val gender: String,
    val height: Double, // Height in centimeters or inches
    val weight: Double, // Weight in kilograms or pounds
    val dietaryPreferences: List<String>,
    val healthConcerns: List<String>
)