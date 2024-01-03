package com.fitness.data.repository

import com.fitness.data.model.cache.user.UserBasicGoalsInfoCache
import com.fitness.data.model.cache.user.UserBasicInfoCache
import com.fitness.data.model.cache.user.UserBasicNutritionInfoCache
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.model.cache.user.UserFitnessLevelCache
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface UserRepository {
    suspend fun getCurrentUserId(): Task<String?>
    suspend fun createUser(user: UserCache): Task<Unit>
    suspend fun readUser(id: String): Task<DocumentSnapshot>
    suspend fun updateUser(user: UserCache): Task<Unit>
    suspend fun deleteUser(id: String): Task<Unit>
    suspend fun createUserBasicInfo(info: UserBasicInfoCache): Task<Unit>
    suspend fun readUserBasicInfo(id: String): Task<DocumentSnapshot>
    suspend fun updateUserBasicInfo(info: UserBasicInfoCache): Task<Unit>
    suspend fun deleteUserBasicInfo(id: String): Task<Unit>

    suspend fun createBasicFitnessInfo(info: UserFitnessLevelCache): Task<Unit>
    suspend fun readBasicFitnessInfo(id: String): Task<DocumentSnapshot>
    suspend fun updateBasicFitnessInfo(info: UserFitnessLevelCache): Task<Unit>
    suspend fun deleteBasicFitnessInfo(id: String): Task<Unit>

    suspend fun createBasicNutritionInfo(info: UserBasicNutritionInfoCache): Task<Unit>
    suspend fun readBasicNutritionInfo(id: String): Task<DocumentSnapshot>
    suspend fun updateBasicNutritionInfo(info: UserBasicNutritionInfoCache): Task<Unit>
    suspend fun deleteBasicNutritionInfo(id: String): Task<Unit>

    suspend fun createUserBasicGoalsInfo(info: UserBasicGoalsInfoCache): Task<Unit>
    suspend fun readUserBasicGoalsInfoCache(id: String): Task<DocumentSnapshot>
    suspend fun updateUserBasicGoalsInfoCache(info: UserBasicGoalsInfoCache): Task<Unit>
    suspend fun deleteUserBasicGoalsInfoCache(id: String): Task<Unit>
}