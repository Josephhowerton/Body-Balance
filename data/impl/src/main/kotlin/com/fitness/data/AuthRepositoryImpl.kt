package com.fitness.data

import com.fitness.data.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): AuthRepository {

    override suspend fun signInWithEmail() {

    }

    override suspend fun signInWithPhone() {

    }

    override suspend fun signInWithGoogle() {

    }

    override suspend fun signInWithFacebook() {

    }

    override suspend fun signInWithX() {

    }

    override suspend fun signUpWithEmail() {

    }

    override suspend fun signUpWithPhone() {
    }

    override suspend fun signUpWithGoogle() {

    }

    override suspend fun signUpWithFacebook() {

    }

    override suspend fun signUpWithX() {

    }

    override suspend fun resetPassword(email: String) {

    }

    override suspend fun signOut() {

    }

}