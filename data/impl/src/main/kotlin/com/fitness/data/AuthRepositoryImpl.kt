package com.fitness.data

import com.fitness.data.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject



class AuthRepositoryImpl @Inject constructor(private val firebase: FirebaseAuth) : AuthRepository {

    override suspend fun signInWithEmail(email: String, password: String): Task<AuthResult> =
        firebase.signInWithEmailAndPassword(email, password)

    override suspend fun sendVerificationCode(phoneNumber: String): Flow<PhoneAuthState> = callbackFlow {

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {}

            override fun onVerificationFailed(e: FirebaseException) {
                trySend(PhoneAuthState.Error(e))
                close(e)
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                trySend(PhoneAuthState.CodeSent(verificationId))
            }
        }

        val options = PhoneAuthOptions.newBuilder(firebase)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
        awaitClose { }
    }
    override suspend fun verifyPhoneNumberWithCode(verificationId: String, code: String): Task<AuthResult> {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        return firebase.signInWithCredential(credential)
    }

    override suspend fun signUpWithEmail(
        firstname: String,
        lastname: String,
        email: String,
        password: String
    ): Task<AuthResult> =
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

    override suspend fun sendPasswordResetEmail(email: String): Task<Unit> =
        firebase.sendPasswordResetEmail(email)
            .onSuccessTask { Tasks.forResult(Unit) }

    override suspend fun signOut(): Unit = firebase.signOut()

}