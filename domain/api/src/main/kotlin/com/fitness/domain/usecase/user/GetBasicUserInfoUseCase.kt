package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.UserBasicInfo
import usecase.DataStateUseCase

abstract class GetBasicUserInfoUseCase: DataStateUseCase<GetBasicUserInfoUseCase.Params, UserBasicInfo>(){
    data class Params(val id: String)
}
