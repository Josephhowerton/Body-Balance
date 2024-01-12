package com.fitness.domain.usecase.user

import cache.cast
import cache.firestore
import com.fitness.data.model.cache.user.UserBasicGoalsInfoCache
import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUserBasicGoalsInfo
import com.fitness.domain.model.user.UserBasicGoalsInfo
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class GetUserBasicGoalsInfoUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : GetUserBasicGoalsInfoUseCase() {

    override suspend fun FlowCollector<DataState<UserBasicGoalsInfo>>.execute(params: Params) {

        when(val firestoreResult = userRepository.readUserBasicGoalsInfo(params.id)){
            is DataState.Success -> {
                val castedResult = cast<UserBasicGoalsInfoCache> { firestoreResult.result }
                val result = firestore { castedResult }.map{ it.toUserBasicGoalsInfo() }
                emit(result)
            }
            is DataState.Error -> {
                emit(firestoreResult)
            }
        }
    }
}