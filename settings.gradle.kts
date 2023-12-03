pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BodyBalance"
include(":app")
include(":library")
include(":common")
include(":common:theme")

include(":features")
include(":features:authentication:authentication")
include(":features:authentication:authentication-api")