package com.fitness.domain.usecase.cache

import cache.firestore
import com.fitness.data.extensions.toUserDomain
import com.fitness.data.repository.UserRepository
import com.fitness.data.model.model.user.UserDomain
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import usecase.LocalUseCase
import javax.inject.Inject

/*
TODO("update implementation")
 */
class ReadUserUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<ReadUserUseCase.Params, UserDomain>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<DataState<UserDomain>>.execute(params: Params) {
        val firestoreResult = userRepository.readUser(params.id)
        val result = firestore{ firestoreResult }.map{ it.toUserDomain() }

        emit(result)
    }
}