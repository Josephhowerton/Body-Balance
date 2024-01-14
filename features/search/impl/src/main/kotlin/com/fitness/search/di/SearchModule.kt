package com.fitness.search.di

import com.fitness.search.nutrition.SearchStateHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    @Singleton
    fun searchStateHolder(): SearchStateHolder = SearchStateHolder

}