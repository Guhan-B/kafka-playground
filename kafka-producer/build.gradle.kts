plugins {
    id("java")
    id("application")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

ext {
    set("mainClass", "com.playground.kafka.producer.Main")

    set("kafkaVersion", "4.1.0")
}

application {
    mainClass = "${project.ext["mainClass"]}"
}



repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:${project.ext["kafkaVersion"]}")
}

tasks.register<Copy>("copy-dependencies") {
    from(configurations.runtimeClasspath)
    into(layout.buildDirectory.dir("libs/dependencies"))
}

tasks.named<Jar>("jar") {
    archiveBaseName.set(project.name)

    manifest {
        attributes(
            "Main-Class" to "${project.ext["mainClass"]}",
            "Class-Path" to configurations.runtimeClasspath.get().files.joinToString(" ") { "dependencies/${it.name}" }
        )
    }

    finalizedBy("copy-dependencies")
}