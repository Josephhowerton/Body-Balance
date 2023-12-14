import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import config.*
import gradle.kotlin.dsl.accessors._c743f248286b75ac50c5d2c5b2791871.debugImplementation

/**
 * Adds a dependency to the `kapt` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
        add("kapt", dependencyNotation)


/**
 * Adds a dependency to the `api` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)


/**
 * Adds a dependency to the `implementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)


/**
 * Adds a dependency to the `TestImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

//Common
val DependencyHandler.AUTHENTICATION_MANAGER get() = api(project(":common:authentication"))
val DependencyHandler.COMPONENTS get() = api(project(":common:components"))
val DependencyHandler.NAVIGATION get() = implementation(project(":common:navigation"))
val DependencyHandler.RESOURCES get() = implementation(project(":common:resources"))
val DependencyHandler.THEME get() = implementation(project(":common:theme"))

//Data
val DependencyHandler.DATA_IMPL get() = api(project(":data:impl"))
val DependencyHandler.DATA_API get() = api(project(":data:api"))

//Domain
val DependencyHandler.DOMAIN get() = api(project(":domain"))

//Feature
val DependencyHandler.AUTHENTICATION get() = implementation(project(":features:authentication:impl"))
val DependencyHandler.AUTHENTICATION_API get() = api(project(":features:authentication:api"))
val DependencyHandler.ONBOARD get() = implementation(project(":features:onboard:impl"))
val DependencyHandler.ONBOARD_API get() = api(project(":features:onboard:api"))

//Library
val DependencyHandler.LIBRARY get() = implementation(project(":library"))


fun DependencyHandler.addCoreDependencies() {
    implementation(CoreLibs.CORE_KTX)
    implementation(CoreLibs.LIFE_RUNTIME)
}

fun DependencyHandler.addJetpackComposeDependencies(){
    implementation(ComposeLibs.ACTIVITY_COMPOSE)
    implementation(platform(ComposeLibs.COMPOSE_BOM))
    implementation(ComposeLibs.COMPOSE_UI)
    implementation(ComposeLibs.COMPOSE_UI_GRAPHICS)
    implementation(ComposeLibs.COMPOSE_TOOLING_PREVIEW)
    implementation(ComposeLibs.COMPOSE_MATERIAL3)
    implementation(ComposeLibs.COMPOSE_CONSTRAINT_LAYOUT)
    debugImplementation(ComposeLibs.DEBUG_COMPOSE_UI_TOOLING)
    debugImplementation(ComposeLibs.DEBUG_COMPOSE_UI_MANIFEST)
}

fun DependencyHandler.addTestDependencies(){
    implementation(TestingLibs.JUNIT)
    implementation(TestingLibs.JUNIT_EXT)
    implementation(TestingLibs.ESPRESSO)
    implementation(TestingLibs.COMPOSE_UI_TEST)
}

fun DependencyHandler.addHiltDependencies() {
    implementation(HiltLibs.HILT)
    kapt(HiltLibs.HILT_COMPILER)
}

fun DependencyHandler.addDaggerDependencies() {
    implementation(DaggerLibs.Dagger2)
    kapt(DaggerLibs.Dagger2Compiler)
}

fun DependencyHandler.addCoroutineDependencies(){
    implementation(CoroutineLibs.Coroutines)
    implementation(CoroutineLibs.CoroutinesViewModel)
}

fun DependencyHandler.addFirebaseDependencies(){
    implementation(platform(FirebaseLibs.FIREBASE_BOM))
    implementation(FirebaseLibs.FIREBASE_AUTHENTICATION)
    implementation(FirebaseLibs.FIREBASE_ANALYTICS)
    implementation(FirebaseLibs.FIREBASE_CRASHALYTICS)
    implementation(FirebaseLibs.FIREBASE_FIRESTORE)
}

fun DependencyHandler.addNavigationDependencies(){
    api(NavigationLibs.COMPOSE_NAVIGATION)
    api(NavigationLibs.NAVIGATION_RUNTIME)
}

fun DependencyHandler.addFeatureDependencies(){
    AUTHENTICATION
    ONBOARD
}

fun DependencyHandler.addFeatureAPIDependencies(){
    AUTHENTICATION_API
    ONBOARD_API
}

fun DependencyHandler.addMedia3Dependencies(){
    api(ExoPlayerLibs.EXO_PLAYER)
    api(ExoPlayerLibs.EXO_PLAYER_UI)
}