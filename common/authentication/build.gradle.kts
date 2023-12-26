plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.authentication"

    composeOptions {
        kotlinCompilerExtensionVersion = config.Configs.KotlinCompilerExtensionVersion
    }
}

dependencies {
    addHiltDependencies()
    addCoroutineDependencies()
    addFirebaseDependencies()
}