plugins {
    id ("commons.android-feature")
}

android {
    namespace = "com.fitness.recipebuilder"
}

dependencies {
    COIL
    FRAMEWORK
    RESOURCES
    COMPONENTS
    DOMAIN_API
    RECIPE_BUILDER_API
    addJetpackComposeDependencies()
}