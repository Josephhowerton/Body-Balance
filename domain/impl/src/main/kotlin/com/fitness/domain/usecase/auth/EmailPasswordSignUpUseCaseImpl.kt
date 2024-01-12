package com.fitness.domain.usecase.auth

import auth.authenticate
import com.fitness.data.repository.auth.AuthRepository
import com.fitness.domain.model.toUserDomain
import com.fitness.domain.model.user.User
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class EmailPasswordSignUpUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : EmailPasswordSignUpUseCase() {


    override suspend fun FlowCollector<DataState<User>>.execute(params: Params) {
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