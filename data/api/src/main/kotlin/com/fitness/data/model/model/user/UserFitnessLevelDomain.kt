package com.fitness.data.model.model.user

data class UserFitnessLevelDomain(
    val userId: String,
    val currentFitnessLevel: String, // e.g., "Beginner", "Intermediate", "Advanced"
    val exerciseHabits: List<String>, // e.g., ["Cardio", "Strength Training"]
    val fitnessGoals: List<String> // e.g., ["Weight Loss", "Build Muscle"]
)