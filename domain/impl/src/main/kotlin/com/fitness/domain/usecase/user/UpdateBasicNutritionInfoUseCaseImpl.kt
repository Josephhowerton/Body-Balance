package com.fitness.domain.usecase.user

import cache.Fields
import com.fitness.data.repository.user.UserRepository
import enums.EDietaryRestrictions
import enums.ENutritionPreferences
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class UpdateBasicNutritionInfoUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : UpdateBasicNutritionInfoUseCase() {
    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = when(params){
            is Params.UpdateRestrictions -> {
                userRepository.updateBasicNutritionInfo(params.id, mapOf(Fields.RESTRICTIONS to params.restrictions, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
            is Params.UpdatePreferences -> {
                userRepository.updateBasicNutritionInfo(params.id, mapOf(Fields.PREFERENCES to params.preferences, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
        }

        emit(result)
    }
}

