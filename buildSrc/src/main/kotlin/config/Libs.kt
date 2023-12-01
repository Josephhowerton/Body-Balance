package config

private object Version {
    const val CORE_KTX = "1.9.0"
    const val LIFECYCLE_RUNTIME = "2.6.2"
    const val ACTIVITY_COMPOSE = "1.7.2"
    const val COMPOSE_BOM = "2023.03.00"
}

object CoreLibs{
    const val CORE_KTX = "androidx.core:core-ktx${Version.CORE_KTX}"
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

object TestingLibs {
    const val JUNIT = "junit:junit:4.13.2"
    const val JUNIT_EXT = "androidx.test.ext:junit:1.1.5"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:3.5.1"
    const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-junit4"
}

object DebuggingLibs {
    const val DEBUG_COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"
    const val DEBUG_COMPOSE_UI_MANIFEST = "androidx.compose.ui:ui-test-manifest"
}