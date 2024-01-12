plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.domain"
}

dependencies {
    LIBRARY
    DOMAIN_API
    addHiltDependencies()
    addCoroutineDependencies()
    addFirebaseDependencies()
}