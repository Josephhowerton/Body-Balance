package com.fitness.domain.usecase.cache

import cache.firestore
import com.fitness.data.extensions.toUserCache
import com.fitness.data.repository.UserRepository
import com.fitness.data.model.model.user.UserDomain
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<CreateUserUseCase.Params, Unit>() {

    data class Params(val userDomainDto: UserDomain)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        params.userDomainDto.isTermAndPrivacyAccepted = true
        val firestoreResult = userRepository.createUser(params.userDomainDto.toUserCache())
        val result = firestore { firestoreResult }
        emit(result)
    }

}