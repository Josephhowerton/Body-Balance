plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.data"
}

dependencies {
    AUTHENTICATION_MANAGER
    addFirebaseDependencies()
    LIBRARY
}