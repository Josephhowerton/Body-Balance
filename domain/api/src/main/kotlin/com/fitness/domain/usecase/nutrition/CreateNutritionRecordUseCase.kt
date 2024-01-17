package com.fitness.domain.usecase.nutrition


import com.fitness.domain.model.nutrition.Recipe
import enums.EMealType
import usecase.DataStateUseCase

abstract class CreateNutritionRecordUseCase: DataStateUseCase<CreateNutritionRecordUseCase.Params, Unit>() {
    data class Params(
        val userId: String,
        val recipe: Recipe,
        val date: Long,
        val hour: Int,
        val minute: Int,
        val mealType: EMealType
    )
}