package com.fitness.authentication.manager

import kotlinx.coroutines.flow.StateFlow

interface AuthenticationManager {
    val authState: StateFlow<AuthState>


}

