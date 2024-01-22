package com.fitness.domain.usecase.nutrition

import com.fitness.data.repository.nutrition.NutritionRecordRepository
import com.fitness.domain.model.nutrition.Nutrition
import com.fitness.domain.model.nutrition.toNutrition
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class GetEditableNutritionRecordsUseCaseImpl @Inject constructor(private val repository: NutritionRecordRepository): GetEditableNutritionRecordsUseCase() {
    override suspend fun FlowCollector<DataState<List<Nutrition>>>.execute(params: Params) {
        when(val dataState = repository.getEditableNutritionRecord(params.userId)){
            is DataState.Success -> {
                emit(DataState.Success(dataState.result.map { it.toNutrition() }))
            }
            is DataState.Error -> emit(dataState)
        }
    }
}