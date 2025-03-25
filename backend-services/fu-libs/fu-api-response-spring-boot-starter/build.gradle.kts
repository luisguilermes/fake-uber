import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

group = "fake.uber.libs.apiresponse"
version = "0.0.1"

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    implementation(libs.spring.boot.autoconfigure)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.jackson)
    testImplementation(libs.spring.boot.starter.test)
}

springBoot {
    mainClass.set(null as String?) // Disables the main class configuration in the starter
}

tasks.bootJar {
    enabled = false // Disables the creation of the executable JAR
}

tasks.withType<BootJar> {
    enabled = false // Ensures that the JAR will not be packaged
}

tasks.withType<BootRun> {
    enabled = false // Ensures that Spring Boot will not attempt to run the application
}
