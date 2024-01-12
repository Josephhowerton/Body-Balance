package com.fitness.domain.usecase.auth


import usecase.DataStateUseCase

abstract class SignOutUseCase: DataStateUseCase<SignOutUseCase.Params, Unit>() {
    object Params

}