import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import config.*

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


/**
 * Adds a dependency to the `DestImplementation` configuration.
 *
 * @param dependencyNotation name of dependency to add at specific configuration
 *
 * @return the dependency
 */
fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)


fun DependencyHandler.annotationProcessor(dependencyNotation: Any): Dependency? =
    add("annotationProcessor", dependencyNotation)


//Common
val DependencyHandler.AUTHENTICATION_MANAGER get() = api(project(":common:authentication"))
val DependencyHandler.COMPONENTS get() = api(project(":common:components"))
val DependencyHandler.NAVIGATION get() = implementation(project(":common:navigation"))
val DependencyHandler.RESOURCES get() = implementation(project(":common:resources"))
val DependencyHandler.THEME get() = implementation(project(":common:theme"))

//Data
val DependencyHandler.DATA_IMPL get() = implementation(project(":data:impl"))
val DependencyHandler.DATA_API get() = api(project(":data:api"))

//Domain
val DependencyHandler.DOMAIN_API  get() = api(project(":domain:api"))
val DependencyHandler.DOMAIN_IMPL get() = implementation(project(":domain:impl"))

//Feature
val DependencyHandler.AUTHENTICATION get() = implementation(project(":features:authentication:impl"))
val DependencyHandler.AUTHENTICATION_API get() = api(project(":features:authentication:api"))
val DependencyHandler.DASHBOARD get() = implementation(project(":features:dashboard:impl"))
val DependencyHandler.DASHBOARD_API get() = api(project(":features:dashboard:api"))
val DependencyHandler.ONBOARD get() = implementation(project(":features:onboard:impl"))
val DependencyHandler.ONBOARD_API get() = api(project(":features:onboard:api"))
val DependencyHandler.RECIPE_BUILDER get() = implementation(project(":features:recipe-builder:impl"))
val DependencyHandler.RECIPE_BUILDER_API get() = api(project(":features:recipe-builder:api"))
val DependencyHandler.SEARCH get() = implementation(project(":features:search:impl"))
val DependencyHandler.SEARCH_API get() = api(project(":features:search:api"))
val DependencyHandler.SIGN_OUT get() = implementation(project(":features:signout:impl"))
val DependencyHandler.SIGN_OUT_API get() = api(project(":features:signout:api"))
val DependencyHandler.USER_PROFILE get() = implementation(project(":features:user-profile:impl"))
val DependencyHandler.USER_PROFILE_API get() = api(project(":features:user-profile:api"))
val DependencyHandler.WELCOME get() = implementation(project(":features:welcome:impl"))
val DependencyHandler.WELCOME_API get() = api(project(":features:welcome:api"))

//Library
val DependencyHandler.LIBRARY get() = implementation(project(":library"))

val DependencyHandler.COIL get() = implementation(CoilLibs.COIL)


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
    implementation(ComposeLibs.MATERIAL)
    implementation(ComposeLibs.COMPOSE_CONSTRAINT_LAYOUT)
    implementation(ComposeLibs.COMPOSE_FOUNDATION)
    debugImplementation(ComposeLibs.DEBUG_COMPOSE_UI_TOOLING)
    debugImplementation(ComposeLibs.DEBUG_COMPOSE_UI_MANIFEST)
}

fun DependencyHandler.addTestDependencies(){
    testImplementation(TestingLibs.JUNIT)
    testImplementation(TestingLibs.JUNIT_EXT)
    testImplementation(TestingLibs.ESPRESSO)
    testImplementation(TestingLibs.COMPOSE_UI_TEST)
}

fun DependencyHandler.addHiltDependencies() {
    implementation(HiltLibs.HILT)
    implementation(HiltLibs.HILT_NAVIGATION)
    kapt(HiltLibs.HILT_COMPILER)
}

fun DependencyHandler.addDaggerDependencies() {
    implementation(DaggerLibs.DAGGER2)
    kapt(DaggerLibs.DAGGER2_COMPILER)
}

fun DependencyHandler.addCoroutineDependencies(){
    implementation(CoroutineLibs.COROUTINES)
    implementation(CoroutineLibs.COROUTINES_ANDROID)
    implementation(CoroutineLibs.COROUTINES_VIEWMODEL)
    implementation(CoroutineLibs.COROUTINES_GOOGLE_PLAY)
}

fun DependencyHandler.addFirebaseDependencies(){
    implementation(platform(FirebaseLibs.FIREBASE_BOM))
    implementation(FirebaseLibs.FIREBASE_AUTHENTICATION)
    implementation(FirebaseLibs.FIREBASE_ANALYTICS)
    implementation(FirebaseLibs.FIREBASE_CRASHALYTICS)
    implementation(FirebaseLibs.FIREBASE_FIRESTORE)
    implementation(FirebaseLibs.GOOGLE_PLAY_AUTH)
    implementation(FirebaseLibs.LIB_PHONE_NUMBER)
}

fun DependencyHandler.addNavigationDependencies(){
    api(NavigationLibs.COMPOSE_NAVIGATION)
    api(NavigationLibs.NAVIGATION_RUNTIME)
}

fun DependencyHandler.addFeatureDependencies(){
    AUTHENTICATION
    DASHBOARD
    ONBOARD
    USER_PROFILE
    WELCOME
    SIGN_OUT
    SEARCH
}

fun DependencyHandler.addFeatureAPIDependencies(){
    AUTHENTICATION_API
    DASHBOARD_API
    ONBOARD_API
    USER_PROFILE_API
    WELCOME_API
    SIGN_OUT_API
    SEARCH_API
}


fun DependencyHandler.addMedia3Dependencies(){
    api(ExoPlayerLibs.EXO_PLAYER)
    api(ExoPlayerLibs.EXO_PLAYER_UI)
}

fun DependencyHandler.addNetworkDependencies(){
    api(NetworkLibs.RETROFIT)
    api(NetworkLibs.RETROFIT_MOSHI)
    api(NetworkLibs.MOSHI)
    api(NetworkLibs.MOSHI_KOTLIN)
    kapt(NetworkLibs.MOSHI_CODEGEN)
    api(NetworkLibs.RETROFIT_COROUTINES)
    api(NetworkLibs.OKHTTP)
    api(NetworkLibs.OKHTTP_INTERCEPTOR)
}

fun DependencyHandler.addSerializationLib(){
    api(SerializationLibs.KOTLINX_SERIALIZATION)
}

fun DependencyHandler.addRoomLib(){
    api(RoomLibs.ROOM_RUNTIME)
    api(RoomLibs.ROOM_KTX)
    api(RoomLibs.GSON)
    kapt(RoomLibs.ROOM_COMPILER)
}