package com.fitness.recipebuilder.di

import com.fitness.recipebuilder.util.RecipeBuilderStateHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeBuilderModule {

    @Provides
    @Singleton
    fun provideRecipeBuilderStateHolder(): RecipeBuilderStateHolder = RecipeBuilderStateHolder


}