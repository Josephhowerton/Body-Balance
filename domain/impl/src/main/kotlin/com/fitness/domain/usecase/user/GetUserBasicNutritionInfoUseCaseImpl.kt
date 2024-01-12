package com.fitness.domain.usecase.user

import cache.cast
import cache.firestore
import com.fitness.data.model.cache.user.UserBasicNutritionInfoCache
import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUserBasicNutritionInfo
import com.fitness.domain.model.user.UserBasicNutritionInfo
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class GetUserBasicNutritionInfoUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : GetUserBasicNutritionInfoUseCase() {

    override suspend fun FlowCollector<DataState<UserBasicNutritionInfo>>.execute(params: Params) {
        when(val firestoreResult = userRepository.readBasicNutritionInfo(params.id)){
            is DataState.Success -> {
                val castedResult = cast<UserBasicNutritionInfoCache> { firestoreResult.result }
                val result = firestore { castedResult }.map{ it.toUserBasicNutritionInfo() }
                emit(result)
            }
            is DataState.Error -> {
                emit(firestoreResult)
            }
        }
    }
}