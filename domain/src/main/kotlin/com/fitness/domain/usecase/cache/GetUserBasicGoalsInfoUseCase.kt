package com.fitness.domain.usecase.cache

import cache.cast
import cache.firestore
import com.fitness.data.extensions.toUserBasicGoalsInfoDomain
import com.fitness.data.model.cache.user.UserBasicGoalsInfoCache
import com.fitness.data.model.domain.user.UserBasicGoalsInfoDomain
import com.fitness.data.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class GetUserBasicGoalsInfoUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<GetUserBasicGoalsInfoUseCase.Params, UserBasicGoalsInfoDomain>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<DataState<UserBasicGoalsInfoDomain>>.execute(params: Params) {

        when(val firestoreResult = userRepository.readUserBasicGoalsInfo(params.id)){
            is DataState.Success -> {
                val castedResult = cast<UserBasicGoalsInfoCache> { firestoreResult.result }
                val result = firestore { castedResult }.map{ it.toUserBasicGoalsInfoDomain() }
                emit(result)
            }
            is DataState.Error -> {
                emit(firestoreResult)
            }
        }
    }
}