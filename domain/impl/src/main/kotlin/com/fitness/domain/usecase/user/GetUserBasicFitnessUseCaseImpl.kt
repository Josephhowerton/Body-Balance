package com.fitness.domain.usecase.user

import cache.cast
import cache.firestore
import com.fitness.data.model.cache.user.UserBasicFitnessLevelCache
import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUserFitnessLevel
import com.fitness.domain.model.user.User
import com.fitness.domain.model.user.UserFitnessLevel
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class GetUserBasicFitnessUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : GetUserBasicFitnessUseCase() {

    override suspend fun FlowCollector<DataState<UserFitnessLevel>>.execute(params: Params) {

        when(val firestoreResult = userRepository.readBasicFitnessInfo(params.id)){
            is DataState.Success -> {
                val castedResult = cast<UserBasicFitnessLevelCache> { firestoreResult.result }
                val result = firestore { castedResult }.map{ it.toUserFitnessLevel() }
                emit(result)
            }
            is DataState.Error -> {
                emit(firestoreResult)
            }
        }
    }
}