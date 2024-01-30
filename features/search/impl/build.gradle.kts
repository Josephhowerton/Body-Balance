plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.search"
}

dependencies {
    COIL
    FRAMEWORK
    RESOURCES
    DOMAIN_API
    SEARCH_API
    DASHBOARD_API
    addJetpackComposeDependencies()
}