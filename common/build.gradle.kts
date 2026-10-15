plugins {
    id("multiloader-common")
    alias(libs.plugins.neoforged.moddev)
}

neoForge {
    neoFormVersion =
        libs.versions.neo.form.version
            .get()
}

dependencies {
    compileOnly(libs.sponge.mixin)
    // fabric and neoforge both bundle mixinextras, so it is safe to use it in common
    compileOnly(libs.mixinextras.common)
    annotationProcessor(libs.mixinextras.common)
}

val commonJava: Configuration =
    configurations.create("commonJava") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }

val commonResources: Configuration =
    configurations.create("commonResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }

artifacts {
    add(
        commonJava.name,
        sourceSets.main
            .get()
            .java.srcDirs
            .single(),
    )
    add(
        commonResources.name,
        sourceSets.main
            .get()
            .resources.srcDirs
            .single(),
    )
}
