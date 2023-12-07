package com.fitness.authentication

sealed class AuthState {
    object UnAuthenticated: AuthState()
    object Authenticated: AuthState()
}