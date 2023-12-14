package com.fitness.data

import com.fitness.data.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): AuthRepository {

    override suspend fun signInWithEmail() {
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

    override suspend fun signUpWithEmail() {
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