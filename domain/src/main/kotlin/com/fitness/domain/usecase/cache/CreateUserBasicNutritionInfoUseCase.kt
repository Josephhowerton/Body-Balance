package com.fitness.domain.usecase.cache

import cache.firestore
import com.fitness.data.extensions.toUserBasicGoalsInfoCache
import com.fitness.data.extensions.toUserBasicNutritionInfoCache
import com.fitness.data.model.domain.user.UserBasicGoalsInfoDomain
import com.fitness.data.model.domain.user.UserBasicNutritionInfoDomain
import com.fitness.data.repository.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class CreateUserBasicNutritionInfoUseCase @Inject constructor(private val userRepository: UserRepository) :
    DataStateUseCase<CreateUserBasicNutritionInfoUseCase.Params, Unit>() {
    data class Params(val userBasicNutritionInfo: UserBasicNutritionInfoDomain)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val firestoreResult = userRepository.createBasicNutritionInfo(info = params.userBasicNutritionInfo.toUserBasicNutritionInfoCache())
        val result = firestore { firestoreResult }
        emit(result)
    }
}