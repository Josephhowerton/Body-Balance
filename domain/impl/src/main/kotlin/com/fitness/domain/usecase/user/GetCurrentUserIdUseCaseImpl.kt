package com.fitness.domain.usecase.user

import com.fitness.data.repository.user.UserRepository
import failure.AuthStateFailure
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.tasks.await
import state.DataState
import javax.inject.Inject

class GetCurrentUserIdUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : GetCurrentUserIdUseCase() {

    override suspend fun FlowCollector<DataState<String>>.execute(params: Params) {
        val currentUserId = userRepository.getCurrentUserId().await()

        if(currentUserId != null) {
            emit(DataState.Success(currentUserId))
        }
        else {
            emit(DataState.Error(AuthStateFailure()))
        }
    }
}