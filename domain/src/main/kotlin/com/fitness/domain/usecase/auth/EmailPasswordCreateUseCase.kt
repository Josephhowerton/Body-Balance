package com.fitness.domain.usecase.auth

import com.fitness.data.repository.AuthRepository
import com.fitness.domain.model.user.User
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class EmailPasswordCreateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : DataStateUseCase<EmailPasswordCreateUseCase.Params, User>() {

    data class Params(val firstname: String, val lastname: String, val email: String, val password: String)

    override suspend fun FlowCollector<DataState<User>>.execute(params: Params) {
    }
}