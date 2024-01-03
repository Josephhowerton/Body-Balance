plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.onboard"
}

dependencies {
    addFirebaseDependencies()
    DOMAIN
    ONBOARD_API
    AUTHENTICATION_API
    SIGN_OUT_API
    THEME
    LIBRARY
    RESOURCES
}