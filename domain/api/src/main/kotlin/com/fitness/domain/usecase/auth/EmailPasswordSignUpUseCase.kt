package com.fitness.domain.usecase.auth

import com.fitness.domain.model.user.User
import usecase.DataStateUseCase

abstract class EmailPasswordSignUpUseCase: DataStateUseCase<EmailPasswordSignUpUseCase.Params, User>() {
    data class Params(val firstname: String, val lastname: String, val email: String, val password: String)
}