package com.fitness.domain.usecase.cache

import cache.cast
import cache.firestore
import com.fitness.data.extensions.toUserDomain
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.model.domain.user.UserDomain
import com.fitness.data.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class GetCurrentUserUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<GetCurrentUserUseCase.Params, UserDomain>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<DataState<UserDomain>>.execute(params: Params) {

        when(val firestoreResult = userRepository.readUser(params.id)){
            is DataState.Success -> {
                val castedResult = cast<UserCache> { firestoreResult.result }
                val result = firestore { castedResult }.map{ it.toUserDomain() }
                emit(result)
            }
            is DataState.Error -> {
                emit(firestoreResult)
            }
        }
    }
}