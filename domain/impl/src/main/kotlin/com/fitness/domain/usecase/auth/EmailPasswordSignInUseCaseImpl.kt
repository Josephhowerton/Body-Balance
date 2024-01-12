package com.fitness.domain.usecase.auth

import auth.authenticate
import com.fitness.data.repository.auth.AuthRepository
import com.fitness.domain.model.toUserDomain
import com.fitness.domain.model.user.User
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class EmailPasswordSignInUseCaseImpl @Inject constructor(private val repository: AuthRepository) : EmailPasswordSignInUseCase() {

    override suspend fun FlowCollector<DataState<User>>.execute(params: Params) {

        val result = repository.signInWithEmail(params.email, params.password)
        val user = authenticate { result }.map { it.toUserDomain() }

        emit(user)
    }
}