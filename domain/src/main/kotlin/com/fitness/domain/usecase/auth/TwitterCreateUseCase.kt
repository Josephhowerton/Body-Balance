package com.fitness.domain.usecase.auth

import com.fitness.domain.model.user.User
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class TwitterCreateUseCase @Inject constructor() : DataStateUseCase<TwitterCreateUseCase.Params, User>() {

    object Params

    override suspend fun FlowCollector<DataState<User>>.execute(params: Params) {
    }
}