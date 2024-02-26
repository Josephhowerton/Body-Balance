package com.fitness.data.repository.user

import cache.firestore
import com.fitness.data.model.cache.metrics.UserBodyMetricsCache
import com.fitness.data.model.cache.metrics.UserRecommendedMacrosCache
import com.fitness.data.model.cache.user.UserBasicGoalsInfoCache
import com.fitness.data.model.cache.user.UserBasicInfoCache
import com.fitness.data.model.cache.user.UserBasicNutritionInfoCache
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.model.cache.user.UserBasicFitnessLevelCache
import com.fitness.data.model.cache.user.UserPreferencesCache
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import state.DataState
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {

    private companion object {
        const val USER: String = "USER"
        const val USER_BASIC_INFO: String = "USER_BASIC_INFO"
        const val USER_BASIC_FITNESS_INFO: String = "USER_BASIC_FITNESS_INFO"
        const val USER_NUTRITIONAL_INFO: String = "USER_NUTRITIONAL_INFO"
        const val USER_GOALS_INFO: String = "USER_GOALS_INFO"
        const val USER_BODY_METRICS: String = "USER_BODY_METRICS"
        const val USER_RECOMMENDED_MACROS: String = "USER_RECOMMENDED_MACROS"

        const val USER_PREFERENCES_FIELD: String = "USER_GOALS_INFO"
    }

    override suspend fun getCurrentUserId(): Task<String?> = Tasks.forResult(firebaseAuth.currentUser?.uid)


    override suspend fun createUser(user: UserCache): DataState<Unit> =
        firestore {
            firestore.collection(USER)
                .document(user.id)
                .set(user)
                .onSuccessTask { Tasks.forResult(Unit) }
        }

    override suspend fun readUser(id: String): DataState<DocumentSnapshot> =
        firestore {
            firestore.collection(USER)
                .document(id)
                .get()
        }

    override suspend fun updateUser(id: String, map: Map<String, Any>): DataState<Unit> =
        firestore {
            firestore.collection(USER)
                .document(id)
                .update(map)
                .onSuccessTask { Tasks.forResult(Unit) }
        }

    override suspend fun deleteUser(id: String): DataState<Unit> =
        firestore {
            firestore.collection(USER)
                .document(id)
                .delete()
                .onSuccessTask { Tasks.forResult(Unit) }
        }

    override suspend fun updateUserPreferences(id: String, preferences: UserPreferencesCache): DataState<Unit> =
        firestore {
            firestore.collection(USER)
                .document(id)
                .update(mapOf(USER_PREFERENCES_FIELD to preferences))
                .onSuccessTask { Tasks.forResult(Unit) }
        }


    override suspend fun updateUserBodyMetrics(id: String, metrics: UserBodyMetricsCache): DataState<Unit>  =
        firestore {
            firestore.collection(USER)
                .document(id)
                .update(mapOf(USER_BODY_METRICS to metrics))
                .onSuccessTask { Tasks.forResult(Unit) }
        }

    override suspend fun updateUserRecommendedMetrics(id: String, macros: UserRecommendedMacrosCache): DataState<Unit> =
        firestore {
            firestore.collection(USER)
                .document(id)
                .update(mapOf(USER_RECOMMENDED_MACROS to macros))
                .onSuccessTask { Tasks.forResult(Unit) }
        }

    override suspend fun createUserBasicInfo(info: UserBasicInfoCache): DataState<Unit> =
        firestore {
            firestore.collection(USER_BASIC_INFO)
                .document(info.userId)
                .set(info)
                .onSuccessTask { Tasks.forResult(Unit) }
        }

    override suspend fun readUserBasicInfo(id: String): DataState<DocumentSnapshot> =
        firestore {
            firestore.collection(USER_BASIC_INFO)
                .document(id)
                .get()
        }


    override suspend fun updateUserBasicInfo(id: String, map: Map<String, Any>): DataState<Unit> =
        firestore {
            firestore.collection(USER_BASIC_INFO)
                .document(id)
                .update(map)
                .onSuccessTask { Tasks.forResult(Unit) }
        }

    override suspend fun deleteUserBasicInfo(id: String): DataState<Unit> =
        firestore {
            firestore.collection(USER_BASIC_INFO)
            .document(id)
            .delete()
            .onSuccessTask{ Tasks.forResult(Unit) }
        }

    override suspend fun createBasicFitnessInfo(info: UserBasicFitnessLevelCache): DataState<Unit> =
        firestore {
            firestore.collection(USER_BASIC_FITNESS_INFO)
                .document(info.userId)
                .set(info)
                .onSuccessTask{ Tasks.forResult(Unit) }
        }


    override suspend fun readBasicFitnessInfo(id: String): DataState<DocumentSnapshot> =
        firestore {
            firestore.collection(USER_BASIC_FITNESS_INFO)
                .document(id)
                .get()
        }


    override suspend fun updateBasicFitnessInfo(id: String, map: Map<String, Any>): DataState<Unit> =
        firestore {
            firestore.collection(USER_BASIC_FITNESS_INFO)
                .document(id)
                .update(map)
                .onSuccessTask { Tasks.forResult(Unit) }
        }


    override suspend fun deleteBasicFitnessInfo(id: String): DataState<Unit> =
        firestore {
            firestore.collection(USER_BASIC_FITNESS_INFO)
                .document(id)
                .delete()
                .onSuccessTask{ Tasks.forResult(Unit) }
        }


    override suspend fun createBasicNutritionInfo(info: UserBasicNutritionInfoCache): DataState<Unit> =
        firestore {
            firestore.collection(USER_NUTRITIONAL_INFO)
                .document(info.userId)
                .set(info)
                .onSuccessTask { Tasks.forResult(Unit) }
        }


    override suspend fun readBasicNutritionInfo(id: String): DataState<DocumentSnapshot> =
        firestore {
            firestore.collection(USER_NUTRITIONAL_INFO)
                .document(id)
                .get()
        }


    override suspend fun updateBasicNutritionInfo(id: String, map: Map<String, Any>): DataState<Unit> =
        firestore {
            firestore.collection(USER_NUTRITIONAL_INFO)
                .document(id)
                .update(map)
                .onSuccessTask { Tasks.forResult(Unit) }
        }

    override suspend fun deleteBasicNutritionInfo(id: String): DataState<Unit> =
        firestore {
            firestore.collection(USER_NUTRITIONAL_INFO)
                .document(id)
                .delete()
                .onSuccessTask{ Tasks.forResult(Unit) }
        }

    override suspend fun createUserBasicGoalsInfo(info: UserBasicGoalsInfoCache): DataState<Unit> =
        firestore {
            firestore.collection(USER_GOALS_INFO)
                .document(info.userId)
                .set(info)
                .onSuccessTask{ Tasks.forResult(Unit) }
        }

    override suspend fun readUserBasicGoalsInfo(id: String): DataState<DocumentSnapshot> =
        firestore {
            firestore.collection(USER_GOALS_INFO)
                .document(id)
                .get()
        }

    override suspend fun updateUserBasicGoalsInfo(id: String, map: Map<String, Any>): DataState<Unit> =
        firestore {
            firestore.collection(USER_NUTRITIONAL_INFO)
                .document(id)
                .update(map)
                .onSuccessTask { Tasks.forResult(Unit) }
        }
    override suspend fun deleteUserBasicGoalsInfo(id: String): DataState<Unit> =
        firestore {
            firestore.collection(USER_GOALS_INFO)
                .document(id)
                .delete()
                .onSuccessTask{ Tasks.forResult(Unit) }
        }
}