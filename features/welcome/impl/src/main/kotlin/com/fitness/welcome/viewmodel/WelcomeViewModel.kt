package com.fitness.welcome.viewmodel

import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.authentication.manager.AuthenticationState
import dagger.hilt.android.lifecycle.HiltViewModel
import state.BaseViewState
import viewmodel.IntentViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val authManager: AuthenticationManager): IntentViewModel<BaseViewState<WelcomeState>, WelcomeEvent>(){

    init {
        onTriggerEvent(WelcomeEvent.AuthCheckEvent)
    }
    override fun onTriggerEvent(event: WelcomeEvent) {
        if(event is WelcomeEvent.AuthCheckEvent){
            onCheckAuthStatus()
        }
    }

    private fun onCheckAuthStatus() {
        setState(
            BaseViewState.Data(
                WelcomeState(isAuthenticated = isAuthenticated(authManager.authState.value))
            )
        )
    }

    private fun isAuthenticated(state: AuthenticationState) = state == AuthenticationState.Authenticated
}