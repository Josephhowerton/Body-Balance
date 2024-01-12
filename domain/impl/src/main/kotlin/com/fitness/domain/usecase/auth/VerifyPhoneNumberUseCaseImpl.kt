package com.fitness.domain.usecase.auth

import auth.phoneAuthenticate
import com.fitness.data.repository.auth.AuthRepository
import com.fitness.domain.model.toUserDomain
import com.fitness.domain.model.user.User
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class VerifyPhoneNumberUseCaseImpl @Inject constructor(private val repository: AuthRepository) : VerifyPhoneNumberUseCase() {

    override suspend fun FlowCollector<DataState<User>>.execute(params: Params) {
        val result = repository.verifyPhoneNumberWithCode(params.verificationId, params.code)
        val user = phoneAuthenticate(params.verificationId) { result }.map { it.toUserDomain() }
        emit(user)
    }
}