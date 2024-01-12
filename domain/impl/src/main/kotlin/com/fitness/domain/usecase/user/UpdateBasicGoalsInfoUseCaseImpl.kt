package com.fitness.domain.usecase.user

import cache.Fields
import com.fitness.data.repository.user.UserRepository
import enums.EGoals
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class UpdateBasicGoalsInfoUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : UpdateBasicGoalsInfoUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.updateUserBasicGoalsInfo(params.id, mapOf(Fields.GOALS to params.goals, Fields.LAST_UPDATED to System.currentTimeMillis()))

        emit(result)
    }
}