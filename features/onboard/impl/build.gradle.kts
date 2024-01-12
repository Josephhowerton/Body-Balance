plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.onboard"
}

dependencies {
    THEME
    LIBRARY
    RESOURCES
    DOMAIN_API
    ONBOARD_API
    SIGN_OUT_API
    AUTHENTICATION_API
    addFirebaseDependencies()
}