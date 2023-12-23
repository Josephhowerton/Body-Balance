package com.fitness.domain.usecase.auth

import com.fitness.data.extensions.toUserDomain
import com.fitness.data.model.model.user.UserDomain
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase
import javax.inject.Inject

class GoogleSignUpUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth) : DataStateUseCase<GoogleSignUpUseCase.Params, UserDomain>() {


    object Params

    override suspend fun FlowCollector<DataState<UserDomain>>.execute(params: Params) {
        FirebaseAuth.getInstance().currentUser?.let {
            emit(DataState.Success(it.toUserDomain()))
        } ?: throw Throwable()
    }
}