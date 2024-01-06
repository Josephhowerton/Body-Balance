package com.fitness.domain.usecase.auth

import auth.PhoneAuthState
import com.fitness.data.repository.AuthRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class SendVerificationCodeUseCase @Inject constructor(private val repository: AuthRepository) :
    DataStateUseCase<SendVerificationCodeUseCase.Params, PhoneAuthState>() {

    data class Params(val phoneNumber: String)

    override suspend fun FlowCollector<DataState<PhoneAuthState>>.execute(params: Params) {
        repository.sendVerificationCode(params.phoneNumber).collect {
            when(it){
                is PhoneAuthState.Error -> throw it.exception
                else -> { emit(DataState.Success(it)) }
            }
        }
    }
}