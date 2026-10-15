import org.gradle.jvm.tasks.Jar

plugins {
    `java-library`
    `maven-publish`
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

val modId = providers.gradleProperty("mod_id").get()
val modName = providers.gradleProperty("mod_name").get()
val modAuthor = providers.gradleProperty("mod_author").get()
val minecraftVersion =
    libs.findVersion("minecraft-version").get().requiredVersion
val javaVersion =
    libs
        .findVersion("java-version")
        .get()
        .requiredVersion
        .toInt()

base {
    archivesName.set("$modId-${project.name}-$minecraftVersion")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

val variants =
    listOf(
        "apiElements",
        "runtimeElements",
        "sourcesElements",
        "javadocElements",
    )

variants.forEach { variant ->
    configurations.named(variant) {
        outgoing {
            capability("${project.group}:${base.archivesName.get()}:${project.version}")
            capability("${project.group}:$modId-${project.name}-$minecraftVersion:${project.version}")
            capability("${project.group}:$modId:${project.version}")
        }
        publishing.publications.withType<MavenPublication>().configureEach {
            suppressPomMetadataWarningsFor(variant)
        }
    }
}

tasks.named<Jar>("sourcesJar") {
    from(rootProject.file("LICENSE")) {
        rename { "${it}_$modName" }
    }
}

tasks.named<Jar>("jar") {
    from(rootProject.file("LICENSE")) {
        rename { "${it}_$modName" }
    }

    manifest {
        attributes(
            mapOf(
                "Specification-Title" to modName,
                "Specification-Vendor" to modAuthor,
                "Specification-Version" to archiveVersion.get(),
                "Implementation-Title" to project.name,
                "Implementation-Version" to archiveVersion.get(),
                "Implementation-Vendor" to modAuthor,
                "Built-On-Minecraft" to minecraftVersion,
            ),
        )
    }
}

var expandProps =
    mapOf(
        "version" to version,
        "group" to project.group, // Else we target the task's group.
        "mod_name" to modName,
        "mod_author" to modAuthor,
        "mod_id" to modId,
        "license" to providers.gradleProperty("license").get(),
        "description" to project.description,
        "credits" to providers.gradleProperty("credits").get(),
        "fabric_loader_version" to libs.findVersion("fabric-loader-version").get().requiredVersion,
        "minecraft_version" to libs.findVersion("minecraft-version").get().requiredVersion,
        "java_version" to libs.findVersion("java-version").get().requiredVersion,
        "minecraft_version_range" to
            providers.gradleProperty("minecraft_version_range").get(),
        "fabric_minecraft_version_range" to
            providers.gradleProperty("fabric_minecraft_version_range").get(),
        "neoforge_version_range" to
            providers.gradleProperty("neoforge_version_range").get(),
        "fabric_loader_version_range" to
            providers.gradleProperty("fabric_loader_version_range").get(),
        "fabric_api_version_range" to
            providers.gradleProperty("fabric_api_version_range").get(),
    )

tasks.named<ProcessResources>("processResources") {
    inputs.properties(expandProps)

    filesMatching(
        listOf(
            "pack.mcmeta",
            "fabric.mod.json",
            "META-INF/mods.toml",
            "META-INF/neoforge.mods.toml",
            "*.mixins.json",
        ),
    ) {
        expand(expandProps)
    }
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }
    repositories {
        providers.environmentVariable("local_maven_url").orNull?.let { repositoryUrl ->
            maven {
                url = uri(repositoryUrl)
            }
        }
    }
}
