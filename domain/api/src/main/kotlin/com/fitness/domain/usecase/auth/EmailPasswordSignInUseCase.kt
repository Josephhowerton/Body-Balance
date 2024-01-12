package com.fitness.domain.usecase.auth

import com.fitness.domain.model.user.User
import usecase.DataStateUseCase

abstract class EmailPasswordSignInUseCase : DataStateUseCase<EmailPasswordSignInUseCase.Params, User>() {
    data class Params(val email: String, val password: String)
}