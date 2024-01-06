plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.data"
}

dependencies {
    addNetworkDependencies()
    addFirebaseDependencies()
    AUTHENTICATION_MANAGER
    LIBRARY
}