package com.fitness.authentication.impl

import com.fitness.authentication.AuthenticationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AuthenticationManagerImpl @Inject constructor(): AuthenticationManager {
    override val authState: StateFlow<AuthState>
        get() = MutableStateFlow(AuthState.UnAuthenticated)

}