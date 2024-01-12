package com.fitness.domain.usecase.auth

import auth.PhoneAuthState
import usecase.DataStateUseCase


abstract class SendVerificationCodeUseCase : DataStateUseCase<SendVerificationCodeUseCase.Params, PhoneAuthState>() {
    data class Params(val phoneNumber: String)

}