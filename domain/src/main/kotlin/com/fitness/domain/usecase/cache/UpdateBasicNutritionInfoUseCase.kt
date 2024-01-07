package com.fitness.domain.usecase.cache

import cache.Fields
import com.fitness.data.repository.user.UserRepository
import enums.EDietaryRestrictions
import enums.ENutritionPreferences
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class UpdateBasicNutritionInfoUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<UpdateBasicNutritionInfoUseCase.Params, Unit>() {

    sealed class Params{
        data class UpdateRestrictions(val id: String, val restrictions: List<EDietaryRestrictions>): Params()
        data class UpdatePreferences(val id: String, val preferences: List<ENutritionPreferences>): Params()
    }

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

