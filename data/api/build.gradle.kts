plugins{
    id("commons.android-library")
    id("kotlin-parcelize")
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