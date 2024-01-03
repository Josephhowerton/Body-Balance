package com.fitness.domain.di

import com.fitness.data.repository.UserRepository
import com.fitness.domain.usecase.cache.CreateBasicUserInfoUseCase
import com.fitness.domain.usecase.cache.CreateUserBasicFitnessUseCase
import com.fitness.domain.usecase.cache.CreateUserBasicGoalsInfoUseCase
import com.fitness.domain.usecase.cache.CreateUserUseCase
import com.fitness.domain.usecase.cache.DeleteUserUseCase
import com.fitness.domain.usecase.cache.GetCurrentUserIdUseCase
import com.fitness.domain.usecase.cache.GetCurrentUserUseCase
import com.fitness.domain.usecase.cache.UpdateUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CacheDomainModule {
    @Provides
    @Singleton
    fun provideCreateBasicUserInfoUseCase(userRepository: UserRepository) = CreateBasicUserInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideCreateUserFitnessLevelUseCase(userRepository: UserRepository) = CreateUserBasicFitnessUseCase(userRepository)

    @Provides
    @Singleton
    fun provideCreateUserGoalUseCase(userRepository: UserRepository) = CreateUserBasicGoalsInfoUseCase(userRepository)

    @Provides
    @Singleton
    fun provideCreateUserUseCase(userRepository: UserRepository) = CreateUserUseCase(userRepository)

    @Provides
    @Singleton
    fun provideDeleteUserUseCase(userRepository: UserRepository) = DeleteUserUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetCurrentUserIdUseCase(userRepository: UserRepository) = GetCurrentUserIdUseCase(userRepository)

    @Provides
    @Singleton
    fun provideGetCurrentUserUseCase(userRepository: UserRepository) = GetCurrentUserUseCase(userRepository)

    @Provides
    @Singleton
    fun provideUpdateUserUseCase(userRepository: UserRepository) = UpdateUserUseCase(userRepository)
}