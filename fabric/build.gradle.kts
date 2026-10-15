plugins {
    id("multiloader-loader")
    alias(libs.plugins.fabric.loom)
}

dependencies {
    minecraft(libs.minecraft)
    implementation(libs.fabric.loader)
    implementation(libs.fabric.api)
}

val modId = providers.gradleProperty("mod_id").get()

loom {
    val aw = project(":common").file("src/main/resources/$modId.accesswidener")
    if (aw.exists()) {
        accessWidenerPath.set(aw)
    }

    runs {
        named("client") {
            client()
            displayName = "Fabric Client"
            generateRunConfig = true
            runDirectory = layout.projectDirectory.dir("runs/client")
        }
        named("server") {
            server()
            displayName = "Fabric Server"
            generateRunConfig = true
            runDirectory = layout.projectDirectory.dir("runs/server")
        }
    }
}
