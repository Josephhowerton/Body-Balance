package com.fitness.domain.usecase.user

import com.fitness.domain.model.user.User
import usecase.DataStateUseCase

abstract class GetCurrentUserUseCase: DataStateUseCase<GetCurrentUserUseCase.Params, User>(){
    data class Params(val id: String)
}
