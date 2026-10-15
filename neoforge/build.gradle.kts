plugins {
    id("multiloader-loader")
    alias(libs.plugins.neoforged.moddev)
}

val modId = providers.gradleProperty("mod_id").get()
val projectPath = path

neoForge {
    version =
        libs.versions.neoforge.version
            .get()
    // Automatically enable neoforge AccessTransformers if the file exists
    val at = project(":common").file("src/main/resources/META-INF/accesstransformer.cfg")
    if (at.exists()) {
        accessTransformers.from(at)
    }
    runs {
        configureEach {
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
            ideName = "NeoForge ${name.replaceFirstChar { it.uppercase() }} ($projectPath)" // Unify the run config names with fabric
        }
        create("client") {
            client()
        }
        create("data") {
            clientData()
        }
        create("server") {
            server()
        }
    }
    mods {
        create(modId) {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets {
    main {
        resources.srcDir("src/generated/resources")
    }
}
