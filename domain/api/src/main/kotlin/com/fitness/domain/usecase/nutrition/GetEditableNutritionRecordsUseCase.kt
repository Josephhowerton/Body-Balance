package com.fitness.domain.usecase.nutrition


import com.fitness.domain.model.nutrition.Nutrition
import usecase.DataStateUseCase

abstract class GetEditableNutritionRecordsUseCase: DataStateUseCase<GetEditableNutritionRecordsUseCase.Params, List<Nutrition>>() {
    data class Params(val userId: String)
}