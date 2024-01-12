package com.fitness.data.repository.edamam

import com.google.android.gms.tasks.Task

interface EdamamAutoCompleteRepository{
    suspend fun autoComplete(q: String, limit: Int = 5): Task<List<String>>
    
}