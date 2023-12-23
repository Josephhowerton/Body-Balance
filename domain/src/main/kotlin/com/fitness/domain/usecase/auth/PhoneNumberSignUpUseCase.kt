package com.fitness.domain.usecase.auth

import com.fitness.data.repository.AuthRepository
import com.fitness.data.model.model.user.UserDomain
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class PhoneNumberSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : DataStateUseCase<PhoneNumberSignUpUseCase.Params, UserDomain>() {

    data class Params(val firstname: String, val lastname: String, val phone: String)
    override suspend fun FlowCollector<DataState<UserDomain>>.execute(params: Params) {
    }
}