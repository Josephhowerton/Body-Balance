plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.domain"
}

dependencies {
    ANALYTICS
    FRAMEWORK
    DOMAIN_API
    addHiltDependencies()
    addCoroutineDependencies()
    addFirebaseDependencies()
}