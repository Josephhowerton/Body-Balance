package com.fitness.domain.usecase.user

import usecase.DataStateUseCase

abstract class DeleteUserBasicGoalsInfoUseCase: DataStateUseCase<DeleteUserBasicGoalsInfoUseCase.Params, Unit>(){
    data class Params(val id: String)
}