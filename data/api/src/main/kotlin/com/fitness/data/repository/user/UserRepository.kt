package com.fitness.data.repository.user

import com.fitness.data.model.cache.user.UserBasicGoalsInfoCache
import com.fitness.data.model.cache.user.UserBasicInfoCache
import com.fitness.data.model.cache.user.UserBasicNutritionInfoCache
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.model.cache.user.UserBasicFitnessLevelCache
import com.fitness.data.model.cache.user.UserPreferencesCache
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import state.DataState

interface UserRepository {
    suspend fun getCurrentUserId(): Task<String?>
    suspend fun createUser(user: UserCache): DataState<Unit>
    suspend fun readUser(id: String): DataState<DocumentSnapshot>
    suspend fun updateUser(id: String, map: Map<String, Any>): DataState<Unit>
    suspend fun deleteUser(id: String): DataState<Unit>
    suspend fun updateUserPreferences(id: String, preferences: UserPreferencesCache): DataState<Unit>

    suspend fun createUserBasicInfo(info: UserBasicInfoCache): DataState<Unit>
    suspend fun readUserBasicInfo(id: String): DataState<DocumentSnapshot>
    suspend fun updateUserBasicInfo(id: String, map: Map<String, Any>): DataState<Unit>
    suspend fun deleteUserBasicInfo(id: String): DataState<Unit>

    suspend fun createBasicFitnessInfo(info: UserBasicFitnessLevelCache): DataState<Unit>
    suspend fun readBasicFitnessInfo(id: String): DataState<DocumentSnapshot>
    suspend fun updateBasicFitnessInfo(id: String, map: Map<String, Any>): DataState<Unit>
    suspend fun deleteBasicFitnessInfo(id: String): DataState<Unit>

    suspend fun createBasicNutritionInfo(info: UserBasicNutritionInfoCache): DataState<Unit>
    suspend fun readBasicNutritionInfo(id: String): DataState<DocumentSnapshot>
    suspend fun updateBasicNutritionInfo(id: String, map: Map<String, Any>): DataState<Unit>
    suspend fun deleteBasicNutritionInfo(id: String): DataState<Unit>

    suspend fun createUserBasicGoalsInfo(info: UserBasicGoalsInfoCache): DataState<Unit>
    suspend fun readUserBasicGoalsInfo(id: String): DataState<DocumentSnapshot>
    suspend fun updateUserBasicGoalsInfo(id: String, map: Map<String, Any>): DataState<Unit>
    suspend fun deleteUserBasicGoalsInfo(id: String): DataState<Unit>
}