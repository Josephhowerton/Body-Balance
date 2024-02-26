package com.fitness.analytics

import enums.EPhysicalActivityLevel


fun calculateTDEE(bmr: Double, activityLevel: EPhysicalActivityLevel): Double {
    return bmr * activityLevel.factor
}

fun calculateProtein(tdee: Double): Double {
    // Define protein percentage (Adjust as needed)
    val proteinPercentage = 0.25 // 25% of total calories from protein
    return (proteinPercentage * tdee) / 4.0 // 4 calories per gram of protein
}

fun calculateFat(tdee: Double): Double {
    // Define fat percentage (Adjust as needed)
    val fatPercentage = 0.25 // 25% of total calories from fat
    return (fatPercentage * tdee) / 9.0 // 9 calories per gram of fat
}

fun calculateCarbs(tdee: Double): Double {
    // Define carbohydrate percentage (Adjust as needed)
    val proteinPercentage = 0.25 // 25% of total calories from protein
    val fatPercentage = 0.25 // 25% of total calories from fat
    val carbPercentage = 1.0 - (proteinPercentage + fatPercentage) // Remaining from carbs
    return (carbPercentage * tdee) / 4.0 // 4 calories per gram of carbohydrate
}

fun calculateFiber(totalCalories: Double): Double {
    // Define fiber percentage (Adjust as needed)
    val fiberPercentage = 0.014 // 14 grams of fiber per 1000 calories
    return fiberPercentage * totalCalories / 1000.0
}
