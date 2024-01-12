package com.fitness.domain.usecase.user

import usecase.DataStateUseCase

abstract class DeleteUserUseCase: DataStateUseCase<DeleteUserUseCase.Params, Unit>(){
    data class Params(val id: String)
}
