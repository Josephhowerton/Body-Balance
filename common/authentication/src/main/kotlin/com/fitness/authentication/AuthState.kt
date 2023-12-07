package com.fitness.authentication

sealed class AuthState {
    object UnAuthentication: AuthState()
    object Authenticated: AuthState()
}