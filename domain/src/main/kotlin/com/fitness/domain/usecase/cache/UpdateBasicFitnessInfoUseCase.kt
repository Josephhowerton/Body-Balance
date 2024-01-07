package com.fitness.domain.usecase.cache

import cache.Fields
import com.fitness.data.repository.user.UserRepository
import enums.EFitnessHabits
import enums.EFitnessLevel
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class UpdateBasicFitnessInfoUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<UpdateBasicFitnessInfoUseCase.Params, Unit>() {

    sealed class Params{
        data class UpdateRestrictions(val id: String, val level: EFitnessLevel): Params()
        data class UpdatePreferences(val id: String, val habits: List<EFitnessHabits>): Params()
    }

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