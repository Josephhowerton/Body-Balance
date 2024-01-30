package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.UserBasicInfo
import com.fitness.domain.model.user.UserPreferences
import usecase.DataStateUseCase

abstract class CreateBasicUserInfoUseCase: DataStateUseCase<CreateBasicUserInfoUseCase.Params, Unit>() {
    data class Params(val userBasicInfoDomain: UserBasicInfo)
}

