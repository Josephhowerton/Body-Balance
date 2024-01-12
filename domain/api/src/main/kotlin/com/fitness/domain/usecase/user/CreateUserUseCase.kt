package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.User
import usecase.DataStateUseCase

abstract class CreateUserUseCase: DataStateUseCase<CreateUserUseCase.Params, Unit>(){
    data class Params(val userDomain: User)
}