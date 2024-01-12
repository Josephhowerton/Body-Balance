package com.fitness.domain.usecase.auth

import com.fitness.domain.model.user.User
import usecase.DataStateUseCase

abstract class VerifyPhoneNumberUseCase: DataStateUseCase<VerifyPhoneNumberUseCase.Params, User>() {
    data class Params(val verificationId: String, val code: String)
}