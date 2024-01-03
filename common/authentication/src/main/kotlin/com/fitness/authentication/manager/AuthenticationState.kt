package com.fitness.authentication.manager

sealed class AuthenticationState {
    object OnBoard: AuthenticationState()
    object UnAuthenticated: AuthenticationState()
    object Authenticated: AuthenticationState()

}