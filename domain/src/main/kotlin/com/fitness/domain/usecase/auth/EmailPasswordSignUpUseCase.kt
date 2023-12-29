package com.fitness.domain.usecase.auth

import auth.authenticate
import com.fitness.data.extensions.toUserDomain
import com.fitness.data.repository.AuthRepository
import com.fitness.data.model.model.user.UserAccountDomain
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class EmailPasswordSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : DataStateUseCase<EmailPasswordSignUpUseCase.Params, UserAccountDomain>() {

    data class Params(val firstname: String, val lastname: String, val email: String, val password: String)

    override suspend fun FlowCollector<DataState<UserAccountDomain>>.execute(params: Params) {
        val result = authRepository.signUpWithEmail(
            firstname = params.firstname,
            lastname = params.lastname,
            email = params.email,
            password = params.password
        )

        val user = authenticate { result }.map { it.toUserDomain() }

        emit(user)
    }
}