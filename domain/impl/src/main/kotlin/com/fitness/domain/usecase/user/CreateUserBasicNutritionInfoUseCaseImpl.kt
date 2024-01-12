package com.fitness.domain.usecase.user

import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUserBasicNutritionInfoCache
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class CreateUserBasicNutritionInfoUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    CreateUserBasicNutritionInfoUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = userRepository.createBasicNutritionInfo(info = params.userBasicNutritionInfo.toUserBasicNutritionInfoCache())
        emit(result)
    }
}