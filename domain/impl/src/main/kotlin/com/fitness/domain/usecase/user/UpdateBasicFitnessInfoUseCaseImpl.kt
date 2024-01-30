package com.fitness.domain.usecase.user

import cache.Fields
import com.fitness.data.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class UpdateBasicFitnessInfoUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : UpdateBasicFitnessInfoUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = when(params){
            is Params.UpdateRestrictions -> {
                userRepository.updateBasicFitnessInfo(params.id, mapOf(Fields.LEVEL to params.level, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
            is Params.UpdatePreferences -> {
                userRepository.updateBasicFitnessInfo(params.id, mapOf(Fields.HABITS to params.habits, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
        }

        emit(result)
    }
}