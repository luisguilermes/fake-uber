plugins {
    application
}

dependencies {
    implementation(libs.ktor.websockets)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
}

application {
    mainClass = "example.fakeuber.supply.satellite.SupplySatelliteAppKt"
}

