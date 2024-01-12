package com.fitness.domain.usecase.user

import usecase.DataStateUseCase

abstract class DeleteBasicUserInfoUseCase : DataStateUseCase<DeleteBasicUserInfoUseCase.Params, Unit>(){
    data class Params(val id: String)
}