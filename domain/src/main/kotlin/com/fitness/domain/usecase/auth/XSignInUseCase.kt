package com.fitness.domain.usecase.auth

import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase

class XSignInUseCase : DataStateUseCase<XSignInUseCase.Params, Nothing>()  {

    object Params

    override suspend fun FlowCollector<DataState<Nothing>>.execute(params: Params) {
    }
}