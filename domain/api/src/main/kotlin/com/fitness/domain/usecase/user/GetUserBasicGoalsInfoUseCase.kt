package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.UserBasicGoalsInfo
import usecase.DataStateUseCase

abstract class GetUserBasicGoalsInfoUseCase: DataStateUseCase<GetUserBasicGoalsInfoUseCase.Params, UserBasicGoalsInfo>(){
    data class Params(val id: String)
}