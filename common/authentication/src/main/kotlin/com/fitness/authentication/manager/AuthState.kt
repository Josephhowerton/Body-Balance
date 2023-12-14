package com.fitness.authentication.manager

sealed class AuthState {
    object UnAuthenticated: AuthState()
    object Authenticated: AuthState()
}