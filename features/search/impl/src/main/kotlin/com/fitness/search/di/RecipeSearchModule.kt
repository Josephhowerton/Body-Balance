package com.fitness.search.di

import com.fitness.search.RecipeSearchStateHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeSearchModule {

    @Provides
    @Singleton
    fun recipeSearchStateHolder(): RecipeSearchStateHolder = RecipeSearchStateHolder

}