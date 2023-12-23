package auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import state.DataState

suspend fun <T: Task<AuthResult>> authenticate(call: () -> T): DataState<FirebaseUser> {
    return try {
        val response = call().await()
        response.user?.let {
            DataState.Success(it)
        } ?: DataState.Error(Exception("Authentication successful but no user found"))
    } catch (e: Exception) {
        DataState.Error(e.handleAuthFailure())
    }
}

