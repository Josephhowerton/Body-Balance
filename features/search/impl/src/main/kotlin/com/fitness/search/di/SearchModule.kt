package com.fitness.search.di

import com.fitness.search.nutrition.NutritionStateHolder
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
    fun nutritionStateHolder(): NutritionStateHolder = NutritionStateHolder

}