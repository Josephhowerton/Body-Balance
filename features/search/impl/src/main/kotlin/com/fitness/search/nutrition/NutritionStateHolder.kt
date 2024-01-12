package com.fitness.search.nutrition

import com.fitness.data.model.network.edamam.food.FoodData
import com.fitness.domain.model.edamam.Recipe

object NutritionStateHolder {
    private var nutritionState: NutritionState = NutritionState()

    fun getState() = nutritionState
    fun updateState(newState: NutritionState) {
        nutritionState = newState
    }

    fun clearState() {
        nutritionState = NutritionState()
    }
}

data class NutritionState(
    val recipes: List<Recipe> = emptyList(),
    val food: List<FoodData> = emptyList()
)
