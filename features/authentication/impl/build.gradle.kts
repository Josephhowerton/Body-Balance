plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.onboard"
}

dependencies {
    addFirebaseDependencies()
    ONBOARD_API
    AUTHENTICATION_API
    LIBRARY
    RESOURCES
    DOMAIN
}