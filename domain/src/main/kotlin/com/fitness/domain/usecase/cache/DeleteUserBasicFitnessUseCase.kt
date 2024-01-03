package com.fitness.domain.usecase.cache

import com.fitness.data.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class DeleteUserBasicFitnessUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<DeleteUserBasicFitnessUseCase.Params, Unit>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.deleteBasicFitnessInfo(params.id)
        emit(result)
    }
}