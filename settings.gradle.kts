pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        exclusiveContent {
            forRepository {
                maven {
                    name = "Fabric"
                    url = uri("https://maven.fabricmc.net")
                }
            }
            filter {
                @Suppress("UnstableApiUsage")
                includeGroupAndSubgroups("net.fabricmc")
            }
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "deathtrack"

include("common")
include("fabric")
include("neoforge")
