package commons

import LIBRARY
import NAVIGATION
import THEME
import COMPONENTS
import addDaggerDependencies
import addHiltDependencies
import addJetpackComposeDependencies
import config.Configs
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.kotlin

plugins{
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

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Configs.KotlinCompilerExtensionVersion
    }
}

dependencies {
    addHiltDependencies()
    addJetpackComposeDependencies()
    THEME
    NAVIGATION
    COMPONENTS
}

kapt {
    correctErrorTypes = true
}