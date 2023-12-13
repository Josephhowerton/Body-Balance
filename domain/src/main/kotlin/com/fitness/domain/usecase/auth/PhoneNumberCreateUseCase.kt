package com.fitness.domain.usecase.auth

import com.fitness.data.repository.AuthRepository
import com.fitness.domain.model.user.User
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class PhoneNumberCreateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : DataStateUseCase<PhoneNumberCreateUseCase.Params, User>() {

    data class Params(val firstname: String, val lastname: String, val phone: String)
    override suspend fun FlowCollector<DataState<User>>.execute(params: Params) {
        TODO("implement phone create use case")
    }
}