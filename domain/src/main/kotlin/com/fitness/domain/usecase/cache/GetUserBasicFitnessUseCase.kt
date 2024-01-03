package com.fitness.domain.usecase.cache

import cache.cast
import cache.firestore
import com.fitness.data.extensions.toUserFitnessLevelDomain
import com.fitness.data.model.cache.user.UserBasicFitnessLevelCache
import com.fitness.data.repository.UserRepository
import com.fitness.data.model.domain.user.UserFitnessLevelDomain
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class GetUserBasicFitnessUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<GetUserBasicFitnessUseCase.Params, UserFitnessLevelDomain>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<DataState<UserFitnessLevelDomain>>.execute(params: Params) {

        when(val firestoreResult = userRepository.readBasicFitnessInfo(params.id)){
            is DataState.Success -> {
                val castedResult = cast<UserBasicFitnessLevelCache> { firestoreResult.result }
                val result = firestore { castedResult }.map{ it.toUserFitnessLevelDomain() }
                emit(result)
            }
            is DataState.Error -> {
                emit(firestoreResult)
            }
        }
    }
}