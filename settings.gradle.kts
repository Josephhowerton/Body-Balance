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

include(":common:navigation")
include(":common:resources")
include(":common:theme")

include(":features")
include(":features:authentication:authentication")
include(":features:authentication:authentication-api")
include(":features:onboard:onboard")
include(":features:onboard:onboard-api")