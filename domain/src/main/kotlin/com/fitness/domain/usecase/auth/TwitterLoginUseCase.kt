package com.fitness.domain.usecase.auth

import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase

class TwitterLoginUseCase : DataStateUseCase<TwitterLoginUseCase.Params, Nothing>()  {

    data class Params(val token: String)

    override suspend fun FlowCollector<DataState<Nothing>>.execute(params: Params) {
    }
}