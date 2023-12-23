package com.fitness.data.repository

import com.fitness.data.model.cache.UserCache
import com.google.android.gms.tasks.Task

interface UserRepository {
    suspend fun createUser(user: UserCache): Task<Unit>
    suspend fun readUser(id: String): Task<UserCache>
    suspend fun updateUser(user: UserCache): Task<Unit>
    suspend fun deleteUser(id: String): Task<Unit>
}