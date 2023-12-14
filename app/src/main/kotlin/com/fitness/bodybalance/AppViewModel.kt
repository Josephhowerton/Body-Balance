package com.fitness.bodybalance

import androidx.lifecycle.ViewModel
import com.fitness.authentication.manager.AuthState
import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.theme.AppTheme
import com.fitness.theme.ThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    authManager: AuthenticationManager,
    themeManager: ThemeManager
) : ViewModel() {

    val authState: StateFlow<AuthState> = authManager.authState
    val appTheme: StateFlow<AppTheme> = themeManager.appTheme
}