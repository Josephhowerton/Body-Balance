package com.fitness.domain.usecase.user

import enums.EGoals
import usecase.DataStateUseCase

abstract class UpdateBasicGoalsInfoUseCase: DataStateUseCase<UpdateBasicGoalsInfoUseCase.Params, Unit>(){
    data class Params(val id: String, val goals: List<EGoals>)
}