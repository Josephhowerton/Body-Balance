plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.navigation"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = config.Configs.KotlinCompilerExtensionVersion
    }
}

dependencies {
    addJetpackComposeDependencies()
    addDaggerDependencies()
    addNavigationDependencies()
    RESOURCES
}
