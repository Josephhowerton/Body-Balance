package com.fitness.domain.usecase.user

import cache.cast
import cache.firestore
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUser
import com.fitness.domain.model.user.User
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class GetCurrentUserUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : GetCurrentUserUseCase() {

    override suspend fun FlowCollector<DataState<User>>.execute(params: Params) {

        when(val firestoreResult = userRepository.readUser(params.id)){
            is DataState.Success -> {
                val castedResult = cast<UserCache> { firestoreResult.result }
                val result = firestore { castedResult }.map{ it.toUser() }
                emit(result)
            }
            is DataState.Error -> {
                emit(firestoreResult)
            }
        }
    }
}