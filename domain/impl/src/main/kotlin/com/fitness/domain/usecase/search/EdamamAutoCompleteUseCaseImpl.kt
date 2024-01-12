package com.fitness.domain.usecase.search

import com.fitness.data.repository.edamam.EdamamAutoCompleteRepository
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EdamamAutoCompleteUseCaseImpl @Inject constructor(private val repository: EdamamAutoCompleteRepository): EdamamAutoCompleteUseCase() {

    override suspend fun FlowCollector<List<String>>.execute(params: Params) {
        val result = repository.autoComplete(q = params.search).await()
        emit(result)
    }
}


