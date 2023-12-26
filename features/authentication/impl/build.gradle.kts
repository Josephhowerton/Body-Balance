plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.authentication"
}

dependencies {
    addFirebaseDependencies()
    ONBOARD_API
    DASHBOARD_API
    AUTHENTICATION_API
    LIBRARY
    RESOURCES
    DOMAIN
}