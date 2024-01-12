package com.fitness.data.repository.edamam

import com.fitness.data.model.network.edamam.NutrientsResponse
import com.fitness.data.network.EdamamNutritionService
import javax.inject.Inject

class EdamamNutritionRepositoryImpl @Inject constructor(
    private val service: EdamamNutritionService
): EdamamNutritionRepository {
    override suspend fun getFoodNutrients(): NutrientsResponse = service.getFoodNutrients()
}