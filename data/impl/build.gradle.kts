plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.data"

}

dependencies {
    addHiltDependencies()
    DATA_API
}