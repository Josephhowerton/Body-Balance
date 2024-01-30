plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.data"

}

dependencies {
    DATA_API
    FRAMEWORK
    addRoomLib()
    addHiltDependencies()
    addFirebaseDependencies()
}