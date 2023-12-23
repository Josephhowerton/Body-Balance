package com.fitness.data

import com.fitness.data.model.cache.UserCache
import com.fitness.data.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRepository {
    override suspend fun createUser(user: UserCache): Task<Unit> =
        firestore.collection("USER")
            .document(user.id)
            .set(user)
            .onSuccessTask {
                Tasks.forResult(Unit)
            }

    override suspend fun readUser(id: String): Task<UserCache> = Tasks.forResult(
        UserCache(
            "",
            "",
            "",
            "",
            "",
            1L
        )
    )

    override suspend fun updateUser(user: UserCache): Task<Unit> = Tasks.forResult(Unit)

    override suspend fun deleteUser(id: String): Task<Unit> = Tasks.forResult(Unit)
}