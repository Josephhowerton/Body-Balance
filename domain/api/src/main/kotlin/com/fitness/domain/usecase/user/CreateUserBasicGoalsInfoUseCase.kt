package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.UserBasicGoalsInfo
import usecase.DataStateUseCase

abstract class CreateUserBasicGoalsInfoUseCase: DataStateUseCase<CreateUserBasicGoalsInfoUseCase.Params, Unit>(){
    data class Params(val userBasicGoalsInfo: UserBasicGoalsInfo)
}