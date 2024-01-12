package com.fitness.bodybalance.viewmodel

import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.authentication.manager.AuthenticationState
import com.fitness.theme.AppTheme
import com.fitness.theme.ThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import viewmodel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val authManager: AuthenticationManager,
    private val themeManager: ThemeManager
) : BaseViewModel() {

    val authState: StateFlow<AuthenticationState> get() = authManager.authState
    val appTheme: StateFlow<AppTheme> get() = themeManager.appTheme

    val showMainHubAnimation: Boolean = authState.value == AuthenticationState.Authenticated

}