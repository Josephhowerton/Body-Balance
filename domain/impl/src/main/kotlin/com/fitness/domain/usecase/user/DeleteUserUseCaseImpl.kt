package com.fitness.domain.usecase.user

import com.fitness.data.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class DeleteUserUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : DeleteUserUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.deleteUser(params.id)
        emit(result)
    }
}

