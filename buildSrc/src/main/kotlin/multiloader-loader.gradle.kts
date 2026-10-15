plugins {
    id("multiloader-common")
}

val commonJava: Configuration =
    configurations.create("commonJava") {
        isCanBeResolved = true
    }

val commonResources: Configuration =
    configurations.create("commonResources") {
        isCanBeResolved = true
    }

val modId = providers.gradleProperty("mod_id").get()

dependencies {
    compileOnly(project(":common")) {
        capabilities {
            requireCapability("${project.group}:$modId")
        }
    }
    add(
        commonJava.name,
        project(
            mapOf(
                "path" to ":common",
                "configuration" to "commonJava",
            ),
        ),
    )
    add(
        commonResources.name,
        project(
            mapOf(
                "path" to ":common",
                "configuration" to "commonResources",
            ),
        ),
    )
}

tasks.named<JavaCompile>("compileJava") {
    dependsOn(commonJava)
    source(commonJava)
}

tasks.named<ProcessResources>("processResources") {
    dependsOn(commonResources)
    from(commonResources)
}

tasks.named<Javadoc>("javadoc") {
    dependsOn(commonJava)
    source(commonJava)
}

tasks.named<Jar>("sourcesJar") {
    dependsOn(commonJava, commonResources)

    from(commonJava)
    from(commonResources)
}
