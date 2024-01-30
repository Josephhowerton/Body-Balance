plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.dashboard"
}

dependencies {
    FRAMEWORK
    RESOURCES
    DASHBOARD_API
    DOMAIN_API
    addFirebaseDependencies()
}