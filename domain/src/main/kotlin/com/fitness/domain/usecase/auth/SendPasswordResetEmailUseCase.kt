package com.fitness.domain.usecase.auth
import cache.firestore
import com.fitness.data.repository.AuthRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject
class SendPasswordResetEmailUseCase @Inject constructor(
    private val repository: AuthRepository
) : DataStateUseCase<SendPasswordResetEmailUseCase.Params, Unit>() {

    data class Params(val email: String)

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val authResult = repository.sendPasswordResetEmail(params.email)
        val result = firestore { authResult }
        emit(result)
    }
}