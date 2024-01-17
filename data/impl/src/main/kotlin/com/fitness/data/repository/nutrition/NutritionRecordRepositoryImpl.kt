package com.fitness.data.repository.nutrition

import cache.firestore
import com.fitness.data.model.cache.nutrition.NutritionEntity
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import state.DataState

class NutritionRecordRepositoryImpl(private val firestore: FirebaseFirestore): NutritionRecordRepository {

    private companion object {
        const val NUTRITION_RECORD: String = "NUTRITION_RECORD"
    }

    override suspend fun createNutritionRecord(nutrition: NutritionEntity) : DataState<Unit> =
        firestore {
            firestore.collection(NUTRITION_RECORD)
                .document(nutrition.recordId)
                .set(nutrition)
                .onSuccessTask { Tasks.forResult(Unit) }
        }

}