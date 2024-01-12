plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.signout"
}

dependencies {
    LIBRARY
    DOMAIN_API
    SIGN_OUT_API
    AUTHENTICATION_API
    AUTHENTICATION_MANAGER
}