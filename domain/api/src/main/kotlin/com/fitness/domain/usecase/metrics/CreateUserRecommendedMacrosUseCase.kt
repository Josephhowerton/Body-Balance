package com.fitness.domain.usecase.metrics

import com.fitness.domain.model.metrics.UserBodyMetrics
import enums.EPhysicalActivityLevel
import usecase.DataStateUseCase

abstract class CreateUserRecommendedMacrosUseCase: DataStateUseCase<CreateUserRecommendedMacrosUseCase.Params, Unit>() {
    data class Params(val bodyMetrics: UserBodyMetrics, val level: EPhysicalActivityLevel)
}

