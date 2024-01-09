pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        jcenter()
        maven("https://jitpack.io")
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "THA4Shop_Nhom4"
include(":app")
