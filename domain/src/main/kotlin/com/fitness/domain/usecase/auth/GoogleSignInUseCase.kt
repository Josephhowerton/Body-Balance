package com.fitness.domain.usecase.auth

import com.fitness.data.extensions.toUserDomain
import com.fitness.data.model.model.user.UserAccountDomain
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import usecase.DataStateUseCase

class GoogleSignInUseCase(private val firebaseAuth: FirebaseAuth) : DataStateUseCase<GoogleSignInUseCase.Params, UserAccountDomain>()  {

    object Params

    override suspend fun FlowCollector<DataState<UserAccountDomain>>.execute(params: Params) {
        firebaseAuth.currentUser?.let {
            emit(DataState.Success(it.toUserDomain()))
        } ?: throw Throwable()
    }
}

