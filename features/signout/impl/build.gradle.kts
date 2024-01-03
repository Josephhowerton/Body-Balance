plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.signout"
}

dependencies {
    SIGN_OUT_API
    AUTHENTICATION_API
    AUTHENTICATION_MANAGER
    LIBRARY
    DOMAIN
}