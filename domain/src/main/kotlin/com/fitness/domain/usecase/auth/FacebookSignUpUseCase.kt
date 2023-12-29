package com.fitness.domain.usecase.auth

import com.fitness.data.model.model.user.UserAccountDomain
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase

class FacebookSignUpUseCase : DataStateUseCase<FacebookSignUpUseCase.Params, UserAccountDomain>()  {

    object Params

    override suspend fun FlowCollector<DataState<UserAccountDomain>>.execute(params: Params) {
    }
}