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


include(":common")
include(":common:authentication")
include(":common:navigation")
include(":common:resources")
include(":common:theme")


include(":features")
include(":features:authentication:impl")
include(":features:authentication:api")
include(":features:onboard:impl")
include(":features:onboard:api")
include(":features:user-profile:impl")
include(":features:user-profile:api")

include(":library")