package com.fitness.data.repository

interface AuthRepository {
    suspend fun signInWithEmail()
    suspend fun signInWithPhone()
    suspend fun signInWithGoogle()
    suspend fun signInWithFacebook()
    suspend fun signInWithX()

    suspend fun signUpWithEmail()
    suspend fun signUpWithPhone()
    suspend fun signUpWithGoogle()
    suspend fun signUpWithFacebook()
    suspend fun signUpWithX()

    suspend fun resetPassword(email: String)
    suspend fun signOut()
}