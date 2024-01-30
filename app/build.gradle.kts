import config.Configs

plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.fitness.bodybalance"
    compileSdk = Configs.CompileSdk

    signingConfigs{
        create("release") {
            keyAlias = "Balance"
            keyPassword = "T@yl0rlh1!"
            storeFile = rootProject.file("balance-keystore.jks")
            storePassword = "T@yl0rlh1!"
        }
    }

    defaultConfig {
        applicationId = "com.fitness.bodybalance"
        minSdk = Configs.MinSdk
        targetSdk = Configs.TargetSdk
        versionCode = Configs.VersionCode
        versionName = Configs.VersionName

        testInstrumentationRunner = Configs.AndroidJunitRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Configs.SourceCompatibility
        targetCompatibility = Configs.TargetCompatibility
    }
    kotlinOptions {
        jvmTarget = Configs.JvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Configs.KotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    addCoreDependencies()
    addJetpackComposeDependencies()
    addTestDependencies()
    addFirebaseDependencies()
    addHiltDependencies()
    addFeatureDependencies()

    THEME
    FRAMEWORK
    NAVIGATION
    RESOURCES
    DATA_IMPL
    DOMAIN_IMPL
}

kapt {
    correctErrorTypes = true
}