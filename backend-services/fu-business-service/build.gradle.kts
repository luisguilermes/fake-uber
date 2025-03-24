group = "fake.uber.business"
version = "0.0.1"

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.jbc)

    implementation(libs.postgresql)
    implementation(libs.postgis.jdbc) {
        exclude(group = "org.postgresql", module = "postgresql")
    }
    implementation(libs.liquibase)
    testImplementation(libs.spring.boot.starter.test)
}