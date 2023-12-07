package com.fitness.bodybalance.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BodyBalanceApplication: Application() {

    @Inject
    lateinit var appProvider: AppProvider

}