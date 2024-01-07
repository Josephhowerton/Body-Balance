package com.fitness.domain.usecase.cache

import cache.Fields
import com.fitness.data.repository.user.UserRepository
import enums.EGender
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class UpdateBasicUserInfoUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<UpdateBasicUserInfoUseCase.Params, Unit>() {

    sealed class Params{
        data class UpdateAge(val id: String, val age: Int): Params()
        data class UpdateGender(val id: String, val gender: EGender): Params()
        data class UpdateHeight(val id: String, val height: Double): Params()
        data class UpdateWeight(val id: String, val weight: Double): Params()
    }

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = when(params){
            is Params.UpdateAge -> {
                userRepository.updateUserBasicInfo(params.id, mapOf(Fields.AGE to params.age, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
            is Params.UpdateGender -> {
                userRepository.updateUserBasicInfo(params.id, mapOf(Fields.GENDER to params.gender, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
            is Params.UpdateHeight -> {
                userRepository.updateUserBasicInfo(params.id, mapOf(Fields.HEIGHT to params.height, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
            is Params.UpdateWeight -> {
                userRepository.updateUserBasicInfo(params.id, mapOf(Fields.WEIGHT to params.weight, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
        }

        emit(result)
    }
}