package com.fitness.authentication

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FirebaseAuthenticationManager @Inject constructor(val firebase: FirebaseAuth): AuthenticationManager {
    override val authState: StateFlow<AuthState>
        get() = MutableStateFlow(AuthState.UnAuthenticated)

    override suspend fun signInWithEmail(credentials: SignInEmailCredentials) {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithEmail(credentials: SignUpEmailCredentials) {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }
}