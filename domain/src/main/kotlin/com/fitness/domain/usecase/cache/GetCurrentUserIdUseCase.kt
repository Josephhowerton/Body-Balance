package com.fitness.domain.usecase.cache

import com.fitness.data.repository.UserRepository
import failure.AuthStateFailure
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.tasks.await
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class GetCurrentUserIdUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<GetCurrentUserIdUseCase.Params, String>() {

    object Params

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