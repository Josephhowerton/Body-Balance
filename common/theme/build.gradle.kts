apply(from = rootProject.file("gradle/scripts/android-library.gradle.kts"))

android{
    namespace = "com.fitness.theme"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = config.Configs.KotlinCompilerExtensionVersion
    }
}

dependencies {
}