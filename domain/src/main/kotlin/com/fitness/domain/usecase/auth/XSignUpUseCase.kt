package com.fitness.domain.usecase.auth

import com.fitness.data.model.model.user.UserDomain
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class XSignUpUseCase @Inject constructor() : DataStateUseCase<XSignUpUseCase.Params, UserDomain>() {

    object Params

    override suspend fun FlowCollector<DataState<UserDomain>>.execute(params: Params) {
    }
}