plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.dashboard"
}

dependencies {
    LIBRARY
    RESOURCES
    DASHBOARD_API
    DOMAIN_API
    addFirebaseDependencies()
}