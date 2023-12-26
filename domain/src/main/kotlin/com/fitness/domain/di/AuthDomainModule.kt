package com.fitness.domain.di

import com.fitness.data.repository.AuthRepository
import com.fitness.domain.usecase.auth.EmailPasswordSignUpUseCase
import com.fitness.domain.usecase.auth.EmailPasswordSignInUseCase
import com.fitness.domain.usecase.auth.FacebookSignUpUseCase
import com.fitness.domain.usecase.auth.FacebookSignInUseCase
import com.fitness.domain.usecase.auth.GoogleSignUpUseCase
import com.fitness.domain.usecase.auth.GoogleSignInUseCase
import com.fitness.domain.usecase.auth.SendPasswordResetEmailUseCase
import com.fitness.domain.usecase.auth.SendVerificationCodeUseCase
import com.fitness.domain.usecase.auth.VerifyPhoneNumberUseCase
import com.fitness.domain.usecase.auth.XSignUpUseCase
import com.fitness.domain.usecase.auth.XLoginUseCase
import com.google.firebase.auth.FirebaseAuth
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
    fun provideEmailPasswordCreateUseCase(authRepository: AuthRepository) = EmailPasswordSignUpUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGoogleCreateUseCase(firebaseAuth: FirebaseAuth) = GoogleSignUpUseCase(firebaseAuth)

    @Provides
    @Singleton
    fun provideSendVerificationCodeUseCase(authRepository: AuthRepository) = SendVerificationCodeUseCase(authRepository)

    @Provides
    @Singleton
    fun provideVerifyPhoneNumberUseCase(authRepository: AuthRepository) = VerifyPhoneNumberUseCase(authRepository)

    @Provides
    @Singleton
    fun provideFacebookCreateUseCase() = FacebookSignUpUseCase()

    @Provides
    @Singleton
    fun provideXCreateUseCase() = XSignUpUseCase()

    @Provides
    @Singleton
    fun provideEmailPasswordLoginUseCase(authRepository: AuthRepository) = EmailPasswordSignInUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGoogleLoginUseCase(firebaseAuth: FirebaseAuth) = GoogleSignInUseCase(firebaseAuth)


    @Provides
    @Singleton
    fun provideXLoginUseCase() = XLoginUseCase()

    @Provides
    @Singleton
    fun provideFacebookLoginUseCase() = FacebookSignInUseCase()

    @Provides
    @Singleton
    fun provideSendPasswordResetEmailUseCase(authRepository: AuthRepository) = SendPasswordResetEmailUseCase(authRepository)

}