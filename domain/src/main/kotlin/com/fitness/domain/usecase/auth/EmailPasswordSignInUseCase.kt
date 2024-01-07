package com.fitness.domain.usecase.auth

import auth.authenticate
import com.fitness.data.extensions.toUserDomain
import com.fitness.data.model.domain.user.UserDomain
import com.fitness.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class EmailPasswordSignInUseCase @Inject constructor(
    private val repository: AuthRepository
) : DataStateUseCase<EmailPasswordSignInUseCase.Params, UserDomain>() {

    data class Params(val email: String, val password: String)

    override suspend fun FlowCollector<DataState<UserDomain>>.execute(params: Params) {

        val result = repository.signInWithEmail(params.email, params.password)
        val user = authenticate { result }.map { it.toUserDomain() }

        emit(user)
    }
}