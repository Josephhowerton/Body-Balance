package com.fitness.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamAutoCompleteService {

    @GET(AUTO_COMPLETE_ENDPOINT)
    suspend fun autoComplete(@Query(QUERY_PARAM) q: String, @Query(LIMIT_PARAM) limit: Int?) : List<String>

    companion object{
        const val AUTO_COMPLETE_ENDPOINT = "auto-complete"
        const val QUERY_PARAM = "q"
        const val LIMIT_PARAM = "limit"
    }
}