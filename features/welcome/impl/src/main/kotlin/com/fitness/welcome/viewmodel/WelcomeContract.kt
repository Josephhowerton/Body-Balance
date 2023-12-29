package com.fitness.welcome.viewmodel

data class WelcomeState(val isAuthenticated: Boolean = false)

sealed class WelcomeEvent{
    object AuthCheckEvent: WelcomeEvent()
}