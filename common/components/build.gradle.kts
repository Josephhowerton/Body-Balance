plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.components"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = config.Configs.KotlinCompilerExtensionVersion
    }
}

dependencies {
    addDaggerDependencies()
    addNavigationDependencies()
    addJetpackComposeDependencies()

    RESOURCES
    LIBRARY
    THEME
    COIL
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
}
