package com.fitness.authentication.util

object AuthStateHolder {
    private var authState: AuthState = AuthState()

    fun getState() = authState
    fun updateState(newState: AuthState) {
        authState = newState
    }

    fun clearState() {
        authState = AuthState()
    }
}

data class AuthState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String? = null,
    val phoneNumber: String? = null,
    val verificationId: String = ""
)
