package com.fitness.domain.usecase.cache

import cache.Fields
import com.fitness.data.repository.UserRepository
import enums.EGoals
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class UpdateBasicGoalsInfoUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<UpdateBasicGoalsInfoUseCase.Params, Unit>() {

    data class Params(val id: String, val goals: List<EGoals>)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.updateUserBasicGoalsInfo(params.id, mapOf(Fields.GOALS to params.goals, Fields.LAST_UPDATED to System.currentTimeMillis()))

        emit(result)
    }
}