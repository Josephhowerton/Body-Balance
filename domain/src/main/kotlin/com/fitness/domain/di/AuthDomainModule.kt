package com.fitness.domain.di

import com.fitness.authentication.di.CommonAuthModule
import com.fitness.authentication.manager.AuthenticationManager
import com.fitness.authentication.manager.AuthenticationManagerImpl
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

@Module(includes = [CommonAuthModule::class])
@InstallIn(SingletonComponent::class)
class AuthDomainModule {
    @Provides
    fun provideEmailPasswordCreateUseCase(authRepository: AuthRepository) = EmailPasswordCreateUseCase(authRepository)

    @Provides
    fun provideGoogleCreateUseCase() = GoogleCreateUseCase()

    @Provides
    fun providePhoneNumberCreateUseCase(authRepository: AuthRepository) = PhoneNumberCreateUseCase(authRepository)

    @Provides
    fun provideTwitterCreateUseCase() = TwitterCreateUseCase()

    @Provides
    fun provideEmailPasswordLoginUseCase(authRepository: AuthRepository) = EmailPasswordLoginUseCase(authRepository)

    @Provides
    fun provideGoogleLoginUseCase() = GoogleLoginUseCase()

    @Provides
    fun providePhoneNumberLoginUseCase() = PhoneNumberLoginUseCase()

    @Provides
    fun provideTwitterLoginUseCase() = TwitterLoginUseCase()

    @Provides
    fun provideForgotPasswordUseCase(authRepository: AuthRepository) = ForgotPasswordUseCase(authRepository)
}