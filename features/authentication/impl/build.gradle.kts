plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.authentication"
}

dependencies {
    addFirebaseDependencies()
    LIBRARY
    RESOURCES
    DOMAIN_API
    ONBOARD_API
    DASHBOARD_API
    AUTHENTICATION_API
}