package com.fitness.authentication

import com.fitness.authentication.impl.AuthState
import kotlinx.coroutines.flow.StateFlow

interface AuthenticationManager {
    val authState: StateFlow<AuthState>


}

