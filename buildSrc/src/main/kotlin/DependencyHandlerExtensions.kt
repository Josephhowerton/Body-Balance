import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import config.CoreLibs
import config.ComposeLibs
import config.TestingLibs
import config.DebuggingLibs
import config.HiltLibs
import config.CoroutineLibs
import config.FirebaseLibs

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
val DependencyHandler.THEME get() = implementation(project(":common:theme"));


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
}

fun DependencyHandler.addTestDependencies(){
    implementation(TestingLibs.JUNIT)
    implementation(TestingLibs.JUNIT_EXT)
    implementation(TestingLibs.ESPRESSO)
    implementation(TestingLibs.COMPOSE_UI_TEST)
}

fun DependencyHandler.addDebuggingDependencies() {
    implementation(DebuggingLibs.DEBUG_COMPOSE_UI_TOOLING)
    implementation(DebuggingLibs.DEBUG_COMPOSE_UI_MANIFEST)
}

fun DependencyHandler.addHiltDependencies() {
    implementation(HiltLibs.HILT)
    implementation(HiltLibs.HILT_VIEW_MODEL)
    kapt(HiltLibs.HILT_COMPILER)
}

fun DependencyHandler.addCoroutineDependencies(){
    implementation(CoroutineLibs.Coroutines)
    implementation(CoroutineLibs.CoroutinesViewModel)
}

fun DependencyHandler.addFirebaseDependecies(){
    implementation(platform(FirebaseLibs.FIREBASE_BOM))
    implementation(FirebaseLibs.FIREBASE_ANALYTICS)
    implementation(FirebaseLibs.FIREBASE_CRASHALYTICS)
    implementation(FirebaseLibs.FIREBASE_FIRESTORE)
}
