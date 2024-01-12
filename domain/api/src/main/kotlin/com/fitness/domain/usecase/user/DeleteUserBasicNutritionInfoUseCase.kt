package com.fitness.domain.usecase.user

import usecase.DataStateUseCase

abstract class DeleteUserBasicNutritionInfoUseCase: DataStateUseCase<DeleteUserBasicNutritionInfoUseCase.Params, Unit>(){
    data class Params(val id: String)
}