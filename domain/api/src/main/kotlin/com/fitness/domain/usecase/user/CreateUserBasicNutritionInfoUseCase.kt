package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.UserBasicNutritionInfo
import usecase.DataStateUseCase

abstract class CreateUserBasicNutritionInfoUseCase : DataStateUseCase<CreateUserBasicNutritionInfoUseCase.Params, Unit>() {
    data class Params(val userBasicNutritionInfo: UserBasicNutritionInfo)
}