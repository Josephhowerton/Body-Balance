plugins{
    id("commons.android-library")
}

//apply(from = rootProject.file("gradle/scripts/android-library.gradle.kts"))

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
    addHiltDependencies()
    addNavigationDependencies()
    RESOURCES
}
