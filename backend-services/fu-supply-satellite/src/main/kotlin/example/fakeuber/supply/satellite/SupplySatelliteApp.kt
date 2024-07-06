package example.fakeuber.supply.satellite

import example.fakeuber.supply.satellite.plugins.configureRouting
import example.fakeuber.supply.satellite.plugins.configureSockets
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSockets()
        configureRouting()
    }.start(wait = true)
}