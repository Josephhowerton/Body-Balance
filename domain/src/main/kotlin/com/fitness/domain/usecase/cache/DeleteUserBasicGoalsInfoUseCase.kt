package com.fitness.domain.usecase.cache

import com.fitness.data.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class DeleteUserBasicGoalsInfoUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<DeleteUserBasicGoalsInfoUseCase.Params, Unit>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.deleteUserBasicGoalsInfo(params.id)
        emit(result)
    }
}