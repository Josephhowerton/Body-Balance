package com.fitness.domain.usecase.user

import cache.cast
import cache.firestore
import com.fitness.data.model.cache.user.UserBasicInfoCache
import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.model.toUserBasicInfo
import com.fitness.domain.model.user.UserBasicInfo
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class GetBasicUserInfoUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : GetBasicUserInfoUseCase() {

    override suspend fun FlowCollector<DataState<UserBasicInfo>>.execute(params: Params) {

        when(val firestoreResult = userRepository.readUserBasicInfo(params.id)){
            is DataState.Success -> {
                val castedResult = cast<UserBasicInfoCache> { firestoreResult.result }
                val result = firestore { castedResult }.map{ it.toUserBasicInfo() }
                emit(result)
            }
            is DataState.Error -> {
                emit(firestoreResult)
            }
        }
    }
}