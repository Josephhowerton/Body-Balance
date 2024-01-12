package com.fitness.domain.usecase.auth
import cache.firestore
import com.fitness.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject
class SendPasswordResetEmailUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SendPasswordResetEmailUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val authResult = repository.sendPasswordResetEmail(params.email)
        val result = firestore { authResult }
        emit(result)
    }
}