package com.fitness.domain.usecase.auth

import auth.PhoneAuthState
import com.fitness.data.repository.auth.AuthRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class SendVerificationCodeUseCaseImpl @Inject constructor(private val repository: AuthRepository) : SendVerificationCodeUseCase() {

    override suspend fun FlowCollector<DataState<PhoneAuthState>>.execute(params: Params) {
        repository.sendVerificationCode(params.phoneNumber).collect {
            when(it){
                is PhoneAuthState.Error -> throw it.exception
                else -> { emit(DataState.Success(it)) }
            }
        }
    }
}