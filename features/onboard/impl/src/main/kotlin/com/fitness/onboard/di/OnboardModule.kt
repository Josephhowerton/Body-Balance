package com.fitness.onboard.di

import com.fitness.onboard.onboard.basic.BasicInformationStateHolder
import com.fitness.onboard.onboard.finalize.FinalizeStateHolder
import com.fitness.onboard.onboard.fitness.FitnessStateHolder
import com.fitness.onboard.onboard.goal.GoalStateHolder
import com.fitness.onboard.onboard.nutrition.NutritionStateHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OnboardModule {

    @Provides
    @Singleton
    fun provideBasicInformationStateHolder(): BasicInformationStateHolder = BasicInformationStateHolder

    @Provides
    @Singleton
    fun provideFitnessStateHolder(): FitnessStateHolder = FitnessStateHolder

    @Provides
    @Singleton
    fun provideNutritionStateHolder(): NutritionStateHolder = NutritionStateHolder

    @Provides
    @Singleton
    fun provideGoalStateHolder(): GoalStateHolder = GoalStateHolder

    @Provides
    @Singleton
    fun provideFinalizeStateHolder(): FinalizeStateHolder = FinalizeStateHolder
}