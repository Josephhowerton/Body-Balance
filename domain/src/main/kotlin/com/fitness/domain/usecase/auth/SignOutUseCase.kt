package com.fitness.domain.usecase.auth


import com.fitness.data.repository.AuthRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val authRepository: AuthRepository) : DataStateUseCase<SignOutUseCase.Params, Unit>() {

    object Params

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        emit(DataState.Success(authRepository.signOut()))
    }
}