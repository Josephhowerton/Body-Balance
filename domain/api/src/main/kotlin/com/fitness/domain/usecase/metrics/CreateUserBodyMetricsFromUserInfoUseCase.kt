package com.fitness.domain.usecase.metrics

import com.fitness.domain.model.user.UserBasicInfo
import com.fitness.domain.model.user.UserFitnessLevel
import enums.ELengthUnit
import enums.EMassUnit
import enums.SystemOfMeasurement
import usecase.DataStateUseCase

abstract class CreateUserBodyMetricsFromUserInfoUseCase: DataStateUseCase<CreateUserBodyMetricsFromUserInfoUseCase.Params, Unit>() {
    data class Params(val info: UserBasicInfo, val unitSystem: SystemOfMeasurement)
}

