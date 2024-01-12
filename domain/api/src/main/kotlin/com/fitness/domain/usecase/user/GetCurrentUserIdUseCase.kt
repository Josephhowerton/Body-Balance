package com.fitness.domain.usecase.user

import usecase.DataStateUseCase

abstract class GetCurrentUserIdUseCase: DataStateUseCase<GetCurrentUserIdUseCase.Params, String>(){
    object Params

}