package com.fitness.authentication

import kotlinx.coroutines.flow.StateFlow

interface AuthenticationManager {
    val authState: StateFlow<AuthState>

    suspend fun signInWithEmail(credentials: SignInEmailCredentials)
    suspend fun signUpWithEmail(credentials: SignUpEmailCredentials)

    suspend fun resetPassword(email: String)
    suspend fun signOut()
}

