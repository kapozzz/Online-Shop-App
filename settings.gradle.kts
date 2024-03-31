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

rootProject.name = "shop-app"
include(":app")
include(":data:client")
include(":features:details")
include(":features:list")
include(":features:entry")
include(":core:ui")
include(":data:remote")
include(":domain")
include(":core:common")
include(":core:presentation")
include(":features:profile")
include(":features:cart")
