package com.fitness.data

import android.os.Parcel
import com.fitness.data.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebase: FirebaseAuth
): AuthRepository {

    override suspend fun signInWithEmail(email: String, password: String): Task<AuthResult> =
        firebase.signInWithEmailAndPassword(email, password)

    override suspend fun signInWithPhone(): Task<AuthResult> =
        TODO()

    override suspend fun signInWithFacebook(): Task<AuthResult> {
        TODO()
    }

    override suspend fun signInWithX(): Task<AuthResult> {
        TODO()
    }

    override suspend fun signUpWithEmail(firstname: String, lastname:String, email: String, password: String): Task<AuthResult> =
        firebase.createUserWithEmailAndPassword(email, password)
            .onSuccessTask { authResult ->
                val profileUpdateTask = authResult?.user?.let {
                    val profileUpdate = UserProfileChangeRequest.Builder()
                        .setDisplayName("$firstname $lastname")
                        .build()

                    it.updateProfile(profileUpdate)
                }

                if (profileUpdateTask != null) {
                    Tasks.whenAllComplete(profileUpdateTask).continueWithTask {
                        Tasks.forResult(authResult)
                    }
                } else {
                    Tasks.forResult(authResult)
                }
            }

    override suspend fun signUpWithPhone(): Task<AuthResult> {
        TODO()
    }

    override suspend fun signUpWithFacebook(): Task<AuthResult> {
        TODO()
    }

    override suspend fun signUpWithX(): Task<AuthResult> {
        TODO()
    }

    override suspend fun sendPasswordResetEmail(email: String): Task<Unit> =
        firebase.sendPasswordResetEmail(email)
            .onSuccessTask { Tasks.forResult(Unit) }

    override suspend fun signOut(): Task<Unit> = Tasks.forResult(firebase.signOut())

}