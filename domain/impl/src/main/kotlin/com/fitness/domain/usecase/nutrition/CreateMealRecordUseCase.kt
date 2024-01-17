package com.fitness.domain.usecase.nutrition

import cache.generateUniqueId
import com.fitness.data.repository.nutrition.NutritionRecordRepository
import com.fitness.domain.model.nutrition.Nutrition
import com.fitness.domain.model.nutrition.toNutritionEntity
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import util.convertToDateTimeString
import javax.inject.Inject

class CreateNutritionRecordUseCaseImpl @Inject constructor(private val repository: NutritionRecordRepository): CreateNutritionRecordUseCase() {
    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val nutrition = Nutrition(
            recordId = generateUniqueId("${params.recipe.recipeId}-${params.userId}"),
            userId = params.userId,
            recipe = params.recipe,
            dateTime = convertToDateTimeString(params.date, params.hour, params.minute),
            mealType = params.mealType
        )

        emit(repository.createNutritionRecord(nutrition.toNutritionEntity()))
    }
}