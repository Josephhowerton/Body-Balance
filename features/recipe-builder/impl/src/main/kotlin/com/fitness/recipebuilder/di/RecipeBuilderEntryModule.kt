package com.fitness.recipebuilder.di

import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import com.fitness.recipebuilder.RecipeBuilderEntry
import com.fitness.recipebuilder.navigation.RecipeBuilderEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RecipeBuilderEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(RecipeBuilderEntry::class)
    fun recipeBuilderEntry(entry: RecipeBuilderEntryImpl): FeatureEntry
}