package com.fitness.data.repository.edamam

import com.fitness.data.model.network.edamam.nutrients.NutrientsResponse

interface EdamamNutritionRepository {
    suspend fun getFoodNutrients(): NutrientsResponse

}