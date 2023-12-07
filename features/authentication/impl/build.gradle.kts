plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.onboard"
}

dependencies {
    AUTHENTICATION_API
    AUTHENTICATION_MANAGER

    addFirebaseDependencies()
}