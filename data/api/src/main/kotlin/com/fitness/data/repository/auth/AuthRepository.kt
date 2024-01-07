package com.fitness.data.repository.auth

import auth.PhoneAuthState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signInWithEmail(email: String, password: String): Task<AuthResult>
    suspend fun sendVerificationCode(phoneNumber: String): Flow<PhoneAuthState>
    suspend fun verifyPhoneNumberWithCode(verificationId: String, code: String): Task<AuthResult>
    suspend fun signUpWithEmail(firstname:String, lastname:String, email: String, password: String): Task<AuthResult>
    suspend fun sendPasswordResetEmail(email: String): Task<Unit>
    suspend fun signOut(): Unit
}