package com.fitness.authentication.di

import com.fitness.domain.di.AuthDomainModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [AuthDomainModule::class])
@InstallIn(SingletonComponent::class)
class FeatureAuthModule