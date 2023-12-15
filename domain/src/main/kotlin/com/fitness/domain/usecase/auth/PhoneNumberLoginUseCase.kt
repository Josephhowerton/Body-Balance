package com.fitness.domain.usecase.auth

import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase

class PhoneNumberLoginUseCase: DataStateUseCase<PhoneNumberLoginUseCase.Params, Nothing>() {

    data class Params(val phoneNumber: String)

    override suspend fun FlowCollector<DataState<Nothing>>.execute(params: Params) {
    }
}