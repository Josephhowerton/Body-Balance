package com.fitness.signout.viewmodel

import android.util.Log
import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.authentication.manager.AuthenticationState
import com.fitness.domain.usecase.auth.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class SignOutViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val authStateManager: AuthenticationManager
): IntentViewModel<BaseViewState<SignOutState>, SignOutEvent>() {

    init { setState(BaseViewState.Data(SignOutState())) }
    override fun onTriggerEvent(event: SignOutEvent) {
        when(event){
            SignOutEvent.SignOut -> onSignOut()
            SignOutEvent.ForceSignOut -> onForceSignOut()
        }
    }

    private fun onSignOut() = safeLaunch {
        execute(signOutUseCase(SignOutUseCase.Params)) {
            authStateManager.update(AuthenticationState.UnAuthenticated)
        }
    }

    private fun onForceSignOut() = safeLaunch {
        execute(signOutUseCase(SignOutUseCase.Params)){
            authStateManager.update(AuthenticationState.UnAuthenticated)
        }
    }
}