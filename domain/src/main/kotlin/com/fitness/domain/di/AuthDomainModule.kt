package com.fitness.domain.di

import com.fitness.data.repository.AuthRepository
import com.fitness.domain.usecase.auth.EmailPasswordCreateUseCase
import com.fitness.domain.usecase.auth.EmailPasswordLoginUseCase
import com.fitness.domain.usecase.auth.ForgotPasswordUseCase
import com.fitness.domain.usecase.auth.GoogleCreateUseCase
import com.fitness.domain.usecase.auth.GoogleLoginUseCase
import com.fitness.domain.usecase.auth.PhoneNumberCreateUseCase
import com.fitness.domain.usecase.auth.PhoneNumberLoginUseCase
import com.fitness.domain.usecase.auth.TwitterCreateUseCase
import com.fitness.domain.usecase.auth.TwitterLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthDomainModule {
    @Provides
    @Singleton
    fun provideEmailPasswordCreateUseCase(authRepository: AuthRepository) = EmailPasswordCreateUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGoogleCreateUseCase() = GoogleCreateUseCase()

    @Provides
    @Singleton
    fun providePhoneNumberCreateUseCase(authRepository: AuthRepository) = PhoneNumberCreateUseCase(authRepository)

    @Provides
    @Singleton
    fun provideTwitterCreateUseCase() = TwitterCreateUseCase()

    @Provides
    @Singleton
    fun provideEmailPasswordLoginUseCase(authRepository: AuthRepository) = EmailPasswordLoginUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGoogleLoginUseCase() = GoogleLoginUseCase()

    @Provides
    @Singleton
    fun providePhoneNumberLoginUseCase() = PhoneNumberLoginUseCase()

    @Provides
    @Singleton
    fun provideTwitterLoginUseCase() = TwitterLoginUseCase()

    @Provides
    @Singleton
    fun provideForgotPasswordUseCase(authRepository: AuthRepository) = ForgotPasswordUseCase(authRepository)
}