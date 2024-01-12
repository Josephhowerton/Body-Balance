package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.UserBasicInfo
import usecase.DataStateUseCase

abstract class CreateBasicUserInfoUseCase: DataStateUseCase<CreateBasicUserInfoUseCase.Params, Unit>() {
    data class Params(val userBasicInfoDomain: UserBasicInfo)
}