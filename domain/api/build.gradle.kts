plugins{
    id("commons.android-library")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version "1.5.0"
}

android {
    namespace = "com.fitness.domain"
}

dependencies {
    LIBRARY
    DATA_API
    AUTHENTICATION_MANAGER
    addRoomLib()
    addNetworkDependencies()
    addFirebaseDependencies()
}