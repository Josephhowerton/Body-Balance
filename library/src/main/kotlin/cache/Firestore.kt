package cache

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import failure.FIFTEEN_SECONDS
import failure.THIRTY_SECONDS
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import state.DataState

inline fun <reified T> cast(call: () -> DocumentSnapshot): Task<T> {
    return try {
        val response = call()
        Tasks.forResult(response.toObject(T::class.java))
    } catch (e: Exception) {
        Tasks.forException(e)
    }
}

suspend fun <T> firestore(call: () -> Task<T>): DataState<T> {
    return try {
        withTimeout(FIFTEEN_SECONDS){
            val response = call().await()
            DataState.Success(response)
        }
    } catch (e: Exception) {
        DataState.Error(e)
    }
}