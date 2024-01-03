package com.fitness.bodybalance.viewmodel

import com.fitness.authentication.manager.AuthenticationState
import com.fitness.theme.AppTheme

data class AppState(
    val authState: AuthenticationState,
    val appTheme: AppTheme,
    val showMainHubAnimation: Boolean,
    val isNewUser: Boolean,
)

sealed class AppEvent {
    object AppStartUp: AppEvent()
}