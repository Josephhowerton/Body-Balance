package com.fitness.domain.usecase.cache

import cache.firestore
import com.fitness.data.extensions.toUserBasicInfoCache
import com.fitness.data.model.cache.user.UserBasicInfoCache
import com.fitness.data.model.domain.user.UserBasicInfoDomain
import com.fitness.data.model.domain.user.UserFitnessLevelDomain
import com.fitness.data.repository.UserRepository
import enums.Gender
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class CreateBasicUserInfoUseCase @Inject constructor(private val userRepository: UserRepository) :
    DataStateUseCase<CreateBasicUserInfoUseCase.Params, Unit>() {
    data class Params(val userBasicInfoDomain: UserBasicInfoDomain)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val firestoreResult = userRepository.createUserBasicInfo(info = params.userBasicInfoDomain.toUserBasicInfoCache())
        val result = firestore { firestoreResult }
        emit(result)
    }
}