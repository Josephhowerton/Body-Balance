package com.fitness.domain.usecase.user

import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUserCache
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class CreateUserUseCaseImpl @Inject constructor(private val userRepository: UserRepository) : CreateUserUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        params.userDomain.isTermAndPrivacyAccepted = true
        val result = userRepository.createUser(params.userDomain.toUserCache())
        emit(result)
    }
}