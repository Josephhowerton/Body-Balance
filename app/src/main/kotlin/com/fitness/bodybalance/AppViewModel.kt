package com.fitness.bodybalance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fitness.authentication.AuthState
import com.fitness.authentication.AuthenticationManager
import com.fitness.theme.AppTheme
import com.fitness.theme.ThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val authManager: AuthenticationManager,
    private val themeManager: ThemeManager
) : ViewModel() {

    val authState: StateFlow<AuthState> = authManager.authState
    
}