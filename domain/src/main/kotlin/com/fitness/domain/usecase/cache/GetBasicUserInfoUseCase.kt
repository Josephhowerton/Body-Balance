package com.fitness.domain.usecase.cache

import cache.cast
import cache.firestore
import com.fitness.data.extensions.toUserBasicInfoDomain
import com.fitness.data.extensions.toUserDomain
import com.fitness.data.model.cache.user.UserBasicInfoCache
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.model.domain.user.UserBasicInfoDomain
import com.fitness.data.repository.UserRepository
import com.fitness.data.model.domain.user.UserDomain
import failure.AuthStateFailure
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.tasks.await
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class GetBasicUserInfoUseCase  @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<GetBasicUserInfoUseCase.Params, UserBasicInfoDomain>() {

    data class Params(val id: String)

    override suspend fun FlowCollector<DataState<UserBasicInfoDomain>>.execute(params: Params) {

        when(val firestoreResult = userRepository.readUserBasicInfo(params.id)){
            is DataState.Success -> {
                val castedResult = cast<UserBasicInfoCache> { firestoreResult.result }
                val result = firestore { castedResult }.map{ it.toUserBasicInfoDomain() }
                emit(result)
            }
            is DataState.Error -> {
                emit(firestoreResult)
            }
        }
    }
}