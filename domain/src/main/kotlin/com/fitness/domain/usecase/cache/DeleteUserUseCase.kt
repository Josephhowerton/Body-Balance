package com.fitness.domain.usecase.cache

import cache.firestore
import com.fitness.data.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.tasks.await
import state.DataState
import usecase.DataStateUseCase
import usecase.LocalUseCase
import javax.inject.Inject

class DeleteUserUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<DeleteUserUseCase.Params, Unit>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val firestoreResult = userRepository.deleteUser(params.id)
        val result = firestore { firestoreResult }
        emit(result)
    }
}