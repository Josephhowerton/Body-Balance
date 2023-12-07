package config

private object Version {
    const val CORE_KTX = "1.12.0"
    const val LIFECYCLE_RUNTIME = "2.6.2"
    const val ACTIVITY_COMPOSE = "1.8.1"
    const val COMPOSE_BOM = "2023.03.00"
    const val COMPOSE_NAVIGATION = "2.7.5"
    const val JUNIT = "4.13.2"
    const val JUNIT_EXT = "1.1.5"
    const val ESPRESSO = "3.5.1"
    const val HILT = "2.44"
    const val DAGGER2 = "2.44"
    const val COROUTINES = "1.3.9"
    const val COROUTINES_VIEWMODEL = "2.4.0"
    const val FIREBASE_BOM = "32.6.0"

}

object CoreLibs{
    const val CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX}"
    const val LIFE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE_RUNTIME}"
}

object ComposeLibs {
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Version.ACTIVITY_COMPOSE}"
    const val COMPOSE_BOM = "androidx.compose:compose-bom:${Version.COMPOSE_BOM}"
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val COMPOSE_TOOLING_PREVIEW  = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3"

}

object NavigationLibs{
    const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:${Version.COMPOSE_NAVIGATION}"
    const val NAVIGATION_RUNTIME = "androidx.navigation:navigation-runtime-ktx:${Version.COMPOSE_NAVIGATION}"
}

object TestingLibs {
    const val JUNIT = "junit:junit:${Version.JUNIT}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${Version.JUNIT_EXT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
    const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-junit4"
}

object DebuggingLibs {
    const val DEBUG_COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"
    const val DEBUG_COMPOSE_UI_MANIFEST = "androidx.compose.ui:ui-test-manifest"
}

object HiltLibs {
    const val HILT = "com.google.dagger:hilt-android:${Version.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILT}"
}

object DaggerLibs{
    const val Dagger2 = "com.google.dagger:dagger:${Version.DAGGER2}"
    const val Dagger2Compiler = "com.google.dagger:dagger-compiler:${Version.DAGGER2}"
}

object CoroutineLibs{
    const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"
    const val CoroutinesViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.COROUTINES_VIEWMODEL}"
}

object FirebaseLibs{
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Version.FIREBASE_BOM}"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics"
    const val FIREBASE_CRASHALYTICS = "com.google.firebase:firebase-crashlytics"
    const val FIREBASE_FIRESTORE = "com.google.firebase:firebase-firestore"
}