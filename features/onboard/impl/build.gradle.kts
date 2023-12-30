plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.onboard"
}

dependencies {
    ONBOARD_API
    AUTHENTICATION_API
    THEME
    LIBRARY
    RESOURCES
}