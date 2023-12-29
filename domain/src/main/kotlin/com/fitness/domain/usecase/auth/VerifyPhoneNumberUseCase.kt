package com.fitness.domain.usecase.auth

import auth.phoneAuthenticate
import com.fitness.data.extensions.toUserDomain
import com.fitness.data.model.model.user.UserAccountDomain
import com.fitness.data.repository.AuthRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class VerifyPhoneNumberUseCase @Inject constructor(private val repository: AuthRepository) : DataStateUseCase<VerifyPhoneNumberUseCase.Params, UserAccountDomain>() {


    data class Params(val verificationId: String, val code: String)

    override suspend fun FlowCollector<DataState<UserAccountDomain>>.execute(params: Params) {
        val result = repository.verifyPhoneNumberWithCode(params.verificationId, params.code)
        val user = phoneAuthenticate(params.verificationId) { result }.map { it.toUserDomain() }
        emit(user)
    }
}