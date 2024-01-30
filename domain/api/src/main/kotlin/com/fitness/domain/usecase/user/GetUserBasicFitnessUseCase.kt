package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.UserFitnessLevel
import usecase.DataStateUseCase

abstract class GetUserBasicFitnessUseCase : DataStateUseCase<GetUserBasicFitnessUseCase.Params, UserFitnessLevel>(){
    data class Params(val id: String)
}