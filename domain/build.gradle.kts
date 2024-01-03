plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.domain"
}

dependencies {
    addHiltDependencies()
    addCoroutineDependencies()
    addFirebaseDependencies()
    DATA_API
    LIBRARY
}