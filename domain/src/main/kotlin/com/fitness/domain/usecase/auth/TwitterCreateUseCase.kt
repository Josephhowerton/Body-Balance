package com.fitness.domain.usecase.auth

import com.fitness.domain.model.user.User
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class TwitterCreateUseCase @Inject constructor() : DataStateUseCase<TwitterCreateUseCase.Params, User>() {

    data class Params(val firstname: String, val lastname: String)

    override suspend fun FlowCollector<DataState<User>>.execute(params: Params) {
        TODO("implement twitter create use case")
    }
}