package com.fitness.data.repository.nutrition

import com.fitness.data.model.cache.nutrition.NutritionEntity
import state.DataState

interface NutritionRecordRepository {
    suspend fun createNutritionRecord(nutrition: NutritionEntity): DataState<Unit>
    suspend fun getEditableNutritionRecord(userId: String): DataState<List<NutritionEntity>>
}