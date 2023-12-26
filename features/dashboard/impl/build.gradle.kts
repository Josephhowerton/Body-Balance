plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.dashboard"
}

dependencies {
    DASHBOARD_API
    LIBRARY
    RESOURCES
    DOMAIN
}