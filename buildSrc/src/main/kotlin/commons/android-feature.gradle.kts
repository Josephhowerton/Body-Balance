package commons

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Configs.JvmTarget
    }

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Configs.KotlinCompilerExtensionVersion
    }
}