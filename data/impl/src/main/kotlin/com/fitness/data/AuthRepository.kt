package com.fitness.data

import com.fitness.authentication.SignInEmailCredentials
import com.fitness.authentication.SignUpEmailCredentials
import com.fitness.data.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): AuthRepository {

    override suspend fun signInWithEmail(credentials: SignInEmailCredentials) {
        TODO("Not yet implemented")
    }

    override suspend fun signInWithPhone() {
        TODO("Not yet implemented")
    }

    override suspend fun signInWithGoogle() {
        TODO("Not yet implemented")
    }

    override suspend fun signInWithFacebook() {
        TODO("Not yet implemented")
    }

    override suspend fun signInWithX() {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithEmail(credentials: SignUpEmailCredentials) {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithPhone() {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithGoogle() {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithFacebook() {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithX() {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }

}