package config

private object Version {
    const val CORE_KTX = "1.12.0"
    const val LIFECYCLE_RUNTIME = "2.6.2"
    const val ACTIVITY_COMPOSE = "1.8.1"
    const val COMPOSE_BOM = "2023.03.00"
    const val COMPOSE_NAVIGATION = "2.7.5"
    const val COMPOSE_CONSTRAINT_LAYOUT = "1.0.1"
    const val JUNIT = "4.13.2"
    const val JUNIT_EXT = "1.1.5"
    const val ESPRESSO = "3.5.1"
    const val HILT = "2.44"
    const val HILT_NAVIGATION = "1.1.0"
    const val DAGGER2 = "2.44"
    const val COROUTINES = "1.7.3"
    const val COROUTINES_VIEWMODEL = "2.6.2"
    const val FIREBASE_BOM = "32.6.0"
    const val EXO_PLAYER = "1.2.0"
    const val GOOGLE_PLAY_AUTH = "20.7.0"
    const val MATERIAL = "1.4.0"
    const val LIB_PHONE_NUMBER = "8.12.39"
    const val GLIDE = "2.2.13"
    const val RETROFIT = "2.9.0"
    const val RETROFIT_COROUTINES = "0.9.2"
    const val OKHTTP = "4.9.0"
    const val MOSHI = "1.14.0"
}

object CoreLibs{
    const val CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX}"
    const val LIFE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE_RUNTIME}"

}

object ComposeLibs{
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Version.ACTIVITY_COMPOSE}"
    const val COMPOSE_BOM = "androidx.compose:compose-bom:${Version.COMPOSE_BOM}"
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val COMPOSE_TOOLING_PREVIEW  = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3:1.1.2"
    const val DEBUG_COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"
    const val DEBUG_COMPOSE_UI_MANIFEST = "androidx.compose.ui:ui-test-manifest"
    const val COMPOSE_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose:${Version.COMPOSE_CONSTRAINT_LAYOUT}"
    const val MATERIAL = "com.google.android.material:material:1.4.0"

}

object NavigationLibs{
    const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:${Version.COMPOSE_NAVIGATION}"
    const val NAVIGATION_RUNTIME = "androidx.navigation:navigation-runtime-ktx:${Version.COMPOSE_NAVIGATION}"
}

object TestingLibs{
    const val JUNIT = "junit:junit:${Version.JUNIT}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${Version.JUNIT_EXT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
    const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-junit4"
}

object HiltLibs{
    const val HILT = "com.google.dagger:hilt-android:${Version.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILT}"
    const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:${Version.HILT_NAVIGATION}"
}

object DaggerLibs{
    const val DAGGER2 = "com.google.dagger:dagger:${Version.DAGGER2}"
    const val DAGGER2_COMPILER = "com.google.dagger:dagger-compiler:${Version.DAGGER2}"
}

object CoroutineLibs{
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"
    const val COROUTINES_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.COROUTINES_VIEWMODEL}"
    const val COROUTINES_GOOGLE_PLAY = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.COROUTINES}"
}

object FirebaseLibs{
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Version.FIREBASE_BOM}"
    const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics"
    const val FIREBASE_CRASHALYTICS = "com.google.firebase:firebase-crashlytics"
    const val FIREBASE_FIRESTORE = "com.google.firebase:firebase-firestore"
    const val FIREBASE_AUTHENTICATION = "com.google.firebase:firebase-auth"
    const val GOOGLE_PLAY_AUTH = "com.google.android.gms:play-services-auth:${Version.GOOGLE_PLAY_AUTH}"
    const val LIB_PHONE_NUMBER = "com.googlecode.libphonenumber:libphonenumber:${Version.LIB_PHONE_NUMBER}"
}

object ExoPlayerLibs{
    const val EXO_PLAYER = "androidx.media3:media3-exoplayer:${Version.EXO_PLAYER}"
    const val EXO_PLAYER_UI = "androidx.media3:media3-ui:${Version.EXO_PLAYER}"
}

object GlideLibs{
    const val GLIDE = "com.github.skydoves:landscapist-glide:${Version.GLIDE}"
}

object NetworkLibs{
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
    const val RETROFIT_MOSHI = "com.squareup.retrofit2:converter-moshi:${Version.RETROFIT}"
    const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Version.MOSHI}"
    const val MOSHI = "com.squareup.moshi:moshi:${Version.MOSHI}"
    const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${Version.MOSHI}"
    const val RETROFIT_COROUTINES = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.RETROFIT_COROUTINES}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Version.OKHTTP}"
    const val OKHTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP}"
}