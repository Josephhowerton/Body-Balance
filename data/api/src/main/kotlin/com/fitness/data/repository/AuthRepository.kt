package com.fitness.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun signInWithEmail(email: String, password: String): Task<AuthResult>
    suspend fun signInWithPhone(): Task<AuthResult>
    suspend fun signInWithFacebook(): Task<AuthResult>
    suspend fun signInWithX(): Task<AuthResult>

    suspend fun signUpWithEmail(firstname:String, lastname:String, email: String, password: String): Task<AuthResult>
    suspend fun signUpWithPhone(): Task<AuthResult>
    suspend fun signUpWithFacebook(): Task<AuthResult>
    suspend fun signUpWithX(): Task<AuthResult>

    suspend fun sendPasswordResetEmail(email: String): Task<Unit>
    suspend fun signOut(): Task<Unit>
}