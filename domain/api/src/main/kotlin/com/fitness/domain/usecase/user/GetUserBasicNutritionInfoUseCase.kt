package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.UserBasicNutritionInfo
import usecase.DataStateUseCase

abstract class GetUserBasicNutritionInfoUseCase : DataStateUseCase<GetUserBasicNutritionInfoUseCase.Params, UserBasicNutritionInfo>(){
    data class Params(val id: String)
}