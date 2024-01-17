package com.fitness.domain.di

   import com.fitness.data.repository.nutrition.NutritionRecordRepository
import com.fitness.domain.usecase.nutrition.CreateNutritionRecordUseCase
import com.fitness.domain.usecase.nutrition.CreateNutritionRecordUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NutritionDomainModule {
    @Provides
    @Singleton
    fun provideCreateNutritionRecordUseCaseUseCase(repository: NutritionRecordRepository): CreateNutritionRecordUseCase = CreateNutritionRecordUseCaseImpl(repository)

}