package com.fitness.data

import com.fitness.data.model.cache.user.UserBasicGoalsInfoCache
import com.fitness.data.model.cache.user.UserBasicInfoCache
import com.fitness.data.model.cache.user.UserBasicNutritionInfoCache
import com.fitness.data.model.cache.user.UserCache
import com.fitness.data.model.cache.user.UserFitnessLevelCache
import com.fitness.data.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
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
    }

    override suspend fun getCurrentUserId(): Task<String?> =
        Tasks.forResult(firebaseAuth.currentUser?.uid)

    override suspend fun createUser(user: UserCache): Task<Unit> =
        firestore.collection(USER)
            .document(user.id)
            .set(user)
            .onSuccessTask { Tasks.forResult(Unit) }

    override suspend fun readUser(id: String): Task<DocumentSnapshot> =
        firestore.collection(USER)
            .document(id)
            .get()

    override suspend fun updateUser(user: UserCache): Task<Unit> = TODO("Update")

    override suspend fun deleteUser(id: String): Task<Unit> =
        firestore.collection(USER)
            .document(id)
            .delete()
            .onSuccessTask { Tasks.forResult(Unit) }

    override suspend fun createUserBasicInfo(info: UserBasicInfoCache): Task<Unit> =
        firestore.collection(USER_BASIC_INFO)
            .document(info.userId)
            .set(info)
            .onSuccessTask { Tasks.forResult(Unit) }

    override suspend fun readUserBasicInfo(id: String): Task<DocumentSnapshot> =
        firestore.collection(USER_BASIC_INFO)
            .document(id)
            .get()

    override suspend fun updateUserBasicInfo(info: UserBasicInfoCache): Task<Unit> = TODO("Update")

    override suspend fun deleteUserBasicInfo(id: String): Task<Unit> =
        firestore.collection(USER_BASIC_INFO)
            .document(id)
            .delete()
            .onSuccessTask { Tasks.forResult(Unit) }

    override suspend fun createBasicFitnessInfo(info: UserFitnessLevelCache): Task<Unit> =
        firestore.collection(USER_BASIC_FITNESS_INFO)
            .document(info.userId)
            .set(info)
            .onSuccessTask { Tasks.forResult(Unit) }

    override suspend fun readBasicFitnessInfo(id: String): Task<DocumentSnapshot> =
        firestore.collection(USER_BASIC_FITNESS_INFO)
            .document(id)
            .get()

    override suspend fun updateBasicFitnessInfo(info: UserFitnessLevelCache): Task<Unit> =
        TODO("Update")

    override suspend fun deleteBasicFitnessInfo(id: String): Task<Unit> =
        firestore.collection(USER_BASIC_FITNESS_INFO)
            .document(id)
            .delete()
            .onSuccessTask { Tasks.forResult(Unit) }

    override suspend fun createBasicNutritionInfo(info: UserBasicNutritionInfoCache): Task<Unit> =
        firestore.collection(USER_NUTRITIONAL_INFO)
            .document(info.userId)
            .set(info)
            .onSuccessTask { Tasks.forResult(Unit) }

    override suspend fun readBasicNutritionInfo(id: String): Task<DocumentSnapshot> =
        firestore.collection(USER_NUTRITIONAL_INFO)
            .document(id)
            .get()

    override suspend fun updateBasicNutritionInfo(info: UserBasicNutritionInfoCache): Task<Unit> = TODO("Not yet implemented")

    override suspend fun deleteBasicNutritionInfo(id: String): Task<Unit> =
        firestore.collection(USER_NUTRITIONAL_INFO)
            .document(id)
            .delete()
            .onSuccessTask { Tasks.forResult(Unit) }

    override suspend fun createUserBasicGoalsInfo(info: UserBasicGoalsInfoCache): Task<Unit> =
        firestore.collection(USER_GOALS_INFO)
            .document(info.userId)
            .set(info)
            .onSuccessTask { Tasks.forResult(Unit) }
    override suspend fun readUserBasicGoalsInfoCache(id: String): Task<DocumentSnapshot> =
        firestore.collection(USER_GOALS_INFO)
            .document(id)
            .get()
    override suspend fun updateUserBasicGoalsInfoCache(info: UserBasicGoalsInfoCache): Task<Unit> = TODO("Not yet implemented")
    override suspend fun deleteUserBasicGoalsInfoCache(id: String): Task<Unit> =
        firestore.collection(USER_GOALS_INFO)
            .document(id)
            .delete()
            .onSuccessTask { Tasks.forResult(Unit) }
}