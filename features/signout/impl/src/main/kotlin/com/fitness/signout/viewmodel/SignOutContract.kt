package com.fitness.signout.viewmodel

import com.fitness.authentication.manager.AuthenticationState

data class SignOutState(val authenticationState: AuthenticationState = AuthenticationState.UnAuthenticated)


sealed class SignOutEvent {
    object ForceSignOut: SignOutEvent()
    object SignOut: SignOutEvent()
}