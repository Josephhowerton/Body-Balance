package com.fitness.search.di

import com.fitness.navigation.FeatureEntry
import com.fitness.navigation.FeatureEntryKey
import com.fitness.search.RecipeSearchEntry
import com.fitness.search.navigation.RecipeSearchEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RecipeSearchEntryModule {
    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(RecipeSearchEntry::class)
    fun recipeSearchEntry(entry: RecipeSearchEntryImpl): FeatureEntry

}