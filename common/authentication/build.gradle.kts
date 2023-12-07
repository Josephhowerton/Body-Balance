plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.authentication"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = config.Configs.KotlinCompilerExtensionVersion
    }
}

dependencies {
    addCoroutineDependencies()
}