package commons

import NAVIGATION
import config.Configs
import config.NavigationLibs.NAVIGATION_RUNTIME
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

plugins {
    kotlin("kapt")
    kotlin("android")
    id("com.android.library")
}

android {
    compileSdk = Configs.CompileSdk

    defaultConfig {
        minSdk = Configs.MinSdk
        testInstrumentationRunner = Configs.AndroidJunitRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = Configs.SourceCompatibility
        targetCompatibility = Configs.TargetCompatibility
    }
    kotlinOptions {
        jvmTarget = Configs.JvmTarget
    }
}


dependencies {
    NAVIGATION
}