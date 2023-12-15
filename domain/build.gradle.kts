plugins{
    id("commons.android-library")
}

android{
    namespace = "com.fitness.domain"

}

dependencies {
    addHiltDependencies()
    DATA_API
    LIBRARY
}