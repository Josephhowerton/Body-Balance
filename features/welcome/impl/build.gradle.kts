plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.welcome"
}

dependencies {
    FRAMEWORK
    RESOURCES
    WELCOME_API
    DASHBOARD_API
    AUTHENTICATION_API
    AUTHENTICATION_MANAGER
}