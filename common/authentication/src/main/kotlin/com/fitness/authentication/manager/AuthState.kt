package com.fitness.authentication.impl

sealed class AuthState {
    object UnAuthenticated: AuthState()
    object Authenticated: AuthState()
}