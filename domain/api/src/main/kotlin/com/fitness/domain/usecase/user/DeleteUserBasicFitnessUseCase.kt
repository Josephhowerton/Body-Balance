package com.fitness.domain.usecase.user

import usecase.DataStateUseCase

abstract class DeleteUserBasicFitnessUseCase : DataStateUseCase<DeleteUserBasicFitnessUseCase.Params, Unit>(){
    data class Params(val id: String)
}