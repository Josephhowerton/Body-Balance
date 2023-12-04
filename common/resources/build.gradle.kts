plugins{
    id("commons.android-library")
}

//apply(from = rootProject.file("gradle/scripts/android-library.gradle.kts"))

android{
    namespace = "com.fitness.resources"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = config.Configs.KotlinCompilerExtensionVersion
    }
}

dependencies {
    addJetpackComposeDependencies()
}