package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.UserFitnessLevel
import usecase.DataStateUseCase

abstract class CreateUserBasicFitnessUseCase : DataStateUseCase<CreateUserBasicFitnessUseCase.Params, Unit>() {
    data class Params(val userBasicInfoDomain: UserFitnessLevel)
}

