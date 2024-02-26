package com.fitness.domain.di

import com.fitness.data.repository.user.UserRepository
import com.fitness.domain.usecase.metrics.CreateUserBodyMetricsFromUserInfoUseCaseImpl
import com.fitness.domain.usecase.metrics.CreateUserBodyMetricsFromUserInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MetricsDomainModule {

    @Provides
    @Singleton
    fun provideCreateUserBodyMetricsFromBasicInfoUseCase(userRepository: UserRepository): CreateUserBodyMetricsFromUserInfoUseCase =
        CreateUserBodyMetricsFromUserInfoUseCaseImpl(userRepository)


    @Provides
    @Singleton
    fun provideCreateUserBodyMetricsFromUserInfoUseCase(userRepository: UserRepository): CreateUserBodyMetricsFromUserInfoUseCase =
        CreateUserBodyMetricsFromUserInfoUseCaseImpl(userRepository)

}