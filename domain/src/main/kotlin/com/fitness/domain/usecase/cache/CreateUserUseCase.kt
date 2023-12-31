package com.fitness.domain.usecase.cache

import cache.firestore
import com.fitness.data.extensions.toUserCache
import com.fitness.data.repository.UserRepository
import com.fitness.data.model.model.user.UserAccountDomain
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : DataStateUseCase<CreateUserUseCase.Params, Unit>() {

    data class Params(val userAccountDomain: UserAccountDomain)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        params.userAccountDomain.isTermAndPrivacyAccepted = true
        val firestoreResult = userRepository.createUser(params.userAccountDomain.toUserCache())
        val result = firestore { firestoreResult }
        emit(result)
    }
}