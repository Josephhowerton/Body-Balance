package com.fitness.domain.usecase.auth

import com.fitness.data.repository.AuthRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) : DataStateUseCase<ForgotPasswordUseCase.Params, Nothing>() {

    data class Params(val email: String, val password: String, val confirmPassword: String)

    override suspend fun FlowCollector<DataState<Nothing>>.execute(params: Params) {
        TODO("implement forgot password use case")
    }
}