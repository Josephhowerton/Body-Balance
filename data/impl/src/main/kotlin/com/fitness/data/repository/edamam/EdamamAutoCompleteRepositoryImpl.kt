package com.fitness.data.repository.edamam

import com.fitness.data.network.EdamamAutoCompleteService
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import javax.inject.Inject

class EdamamAutoCompleteRepositoryImpl @Inject constructor(private val service: EdamamAutoCompleteService): EdamamAutoCompleteRepository {
    override suspend fun autoComplete(q: String, limit: Int): Task<List<String>> = Tasks.forResult(service.autoComplete(q, limit))
}

