package com.fitness.domain.usecase.auth


import com.fitness.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(private val authRepository: AuthRepository) : SignOutUseCase() {
    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        emit(DataState.Success(authRepository.signOut()))
    }
}