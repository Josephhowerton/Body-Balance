package com.fitness.domain.usecase.cache

import com.fitness.data.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class DeleteUserBasicNutritionInfoUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<DeleteUserBasicNutritionInfoUseCase.Params, Unit>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.deleteBasicNutritionInfo(params.id)
        emit(result)
    }
}
