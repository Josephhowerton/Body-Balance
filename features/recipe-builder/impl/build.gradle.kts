plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.recipebuilder"
}

dependencies {
    COIL
    LIBRARY
    RESOURCES
    DOMAIN_API
    RECIPE_BUILDER_API
    addJetpackComposeDependencies()
}