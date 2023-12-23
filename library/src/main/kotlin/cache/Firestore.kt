package cache

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import state.DataState

suspend fun <T> firestore(call: () -> Task<T>): DataState<T> {
    return try {
        val response = call().await()
        DataState.Success(response)
    } catch (e: Exception) {
        DataState.Error(e)
    }
}