package com.fitness.domain.usecase.auth

import com.fitness.data.model.model.user.UserDomain
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase

class FacebookSignUpUseCase : DataStateUseCase<FacebookSignUpUseCase.Params, UserDomain>()  {

    object Params

    override suspend fun FlowCollector<DataState<UserDomain>>.execute(params: Params) {
    }
}