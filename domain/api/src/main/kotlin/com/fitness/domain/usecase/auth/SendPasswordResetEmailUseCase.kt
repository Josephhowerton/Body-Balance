package com.fitness.domain.usecase.auth
import usecase.DataStateUseCase

abstract class SendPasswordResetEmailUseCase : DataStateUseCase<SendPasswordResetEmailUseCase.Params, Unit>() {
    data class Params(val email: String)
}