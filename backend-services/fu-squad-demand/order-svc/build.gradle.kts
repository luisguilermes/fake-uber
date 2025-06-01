group = "fake.uber.demand.order"
version = "0.0.1"

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.jpa)
    implementation(libs.prometheus.registry)
    implementation(libs.postgresql)
    implementation(libs.postgis.jdbc)
    implementation(libs.liquibase)

    testImplementation(libs.spring.boot.starter.test)
}
