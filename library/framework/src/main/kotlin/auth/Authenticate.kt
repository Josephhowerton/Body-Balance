package auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import failure.THIRTY_SECONDS
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import state.DataState

suspend fun <T: Task<AuthResult>> authenticate(call: () -> T): DataState<FirebaseUser> {
    return try {
        withTimeout(THIRTY_SECONDS) {
            val response = call().await()
            response.user?.let {
                DataState.Success(it)
            } ?: DataState.Error(Exception("Authentication successful but no user found"))
        }
    } catch (e: Exception) {
        DataState.Error(e)
    }
}

suspend fun <T: Task<AuthResult>> phoneAuthenticate(verificationId: String, call: () -> T): DataState<FirebaseUser> =
    try {
        withTimeout(THIRTY_SECONDS){
            val response = call().await()
            response.user?.let {
                DataState.Success(it)
            } ?: DataState.Error(Exception("Authentication successful but no user found"))
        }
    } catch (e: Exception) {
        if(e is FirebaseAuthInvalidCredentialsException){
            DataState.Error(PhoneVerificationError(verificationId, e.errorCode, "The code you entered is invalid. Please try again."))
        }
        else{
            DataState.Error(e)
        }
    }

