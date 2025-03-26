group = "fake.uber.business"
version = "0.0.1"

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    implementation(project(":fu-libs:fu-api-response-spring-boot-starter"))

    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.jbc)
    implementation(libs.prometheus.registry)
    implementation(libs.postgresql)
    implementation(libs.postgis.jdbc) {
        exclude(group = "org.postgresql", module = "postgresql")
    }
    implementation(libs.liquibase)

    testImplementation(libs.spring.boot.starter.test)
}
