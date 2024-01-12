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
include(":common:components")
include(":common:navigation")
include(":common:resources")
include(":common:theme")

include(":data:api")
include(":data:impl")
include(":domain:api")
include(":domain:impl")

include(":features")
include(":features:authentication:impl")
include(":features:authentication:api")
include(":features:dashboard:impl")
include(":features:dashboard:api")
include(":features:onboard:impl")
include(":features:onboard:api")
include(":features:search:impl")
include(":features:search:api")
include(":features:signout:impl")
include(":features:signout:api")
include(":features:user-profile:impl")
include(":features:user-profile:api")
include(":features:welcome:impl")
include(":features:welcome:api")

include(":library")
