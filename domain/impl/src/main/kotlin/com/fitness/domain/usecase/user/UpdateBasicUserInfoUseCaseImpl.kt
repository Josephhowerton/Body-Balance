package com.fitness.domain.usecase.user

import cache.Fields
import com.fitness.data.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class UpdateBasicUserInfoUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : UpdateBasicUserInfoUseCase() {

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