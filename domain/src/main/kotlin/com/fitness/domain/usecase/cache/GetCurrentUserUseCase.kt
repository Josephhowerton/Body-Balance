package com.fitness.domain.usecase.cache

import cache.cast
import cache.firestore
import com.fitness.data.extensions.toUserDomain
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.repository.UserRepository
import com.fitness.data.model.domain.user.UserDomain
import failure.AuthStateFailure
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.tasks.await
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class GetCurrentUserUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<GetCurrentUserUseCase.Params, UserDomain>() {

    object Params

    override suspend fun FlowCollector<DataState<UserDomain>>.execute(params: Params) {
        val currentUserId = userRepository.getCurrentUserId().await()

        if(currentUserId != null) {
            val firestoreResult = userRepository.readUser(currentUserId)
            val castedResult = cast<UserCache> { firestoreResult }
            val result = firestore { castedResult }.map{ it.toUserDomain() }
            emit(result)
        }
        else {
            emit(DataState.Error(AuthStateFailure()))
        }
    }
}