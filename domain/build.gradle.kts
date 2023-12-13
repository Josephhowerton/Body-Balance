plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.domain"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = config.Configs.KotlinCompilerExtensionVersion
    }
}

dependencies {
    addHiltDependencies()
    DATA
    LIBRARY
}