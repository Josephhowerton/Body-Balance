plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.domain"
}

dependencies {
    FRAMEWORK
    DOMAIN_API
    addHiltDependencies()
    addCoroutineDependencies()
    addFirebaseDependencies()
}