package com.fitness.domain.usecase.cache

import cache.cast
import cache.firestore
import com.fitness.data.extensions.toUserBasicNutritionInfoDomain
import com.fitness.data.extensions.toUserDomain
import com.fitness.data.model.cache.user.UserBasicNutritionInfoCache
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.model.domain.user.UserBasicNutritionInfoDomain
import com.fitness.data.repository.UserRepository
import com.fitness.data.model.domain.user.UserDomain
import failure.AuthStateFailure
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.tasks.await
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class GetUserBasicNutritionInfoUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<GetUserBasicNutritionInfoUseCase.Params, UserBasicNutritionInfoDomain>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<DataState<UserBasicNutritionInfoDomain>>.execute(params: Params) {

        when(val firestoreResult = userRepository.readBasicNutritionInfo(params.id)){
            is DataState.Success -> {
                val castedResult = cast<UserBasicNutritionInfoCache> { firestoreResult.result }
                val result = firestore { castedResult }.map{ it.toUserBasicNutritionInfoDomain() }
                emit(result)
            }
            is DataState.Error -> {
                emit(firestoreResult)
            }
        }
    }
}