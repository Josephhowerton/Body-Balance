package com.fitness.domain.usecase.search

import usecase.LocalUseCase

abstract class EdamamAutoCompleteUseCase: LocalUseCase<EdamamAutoCompleteUseCase.Params, List<String>>(){
    data class Params(val search: String)
}


