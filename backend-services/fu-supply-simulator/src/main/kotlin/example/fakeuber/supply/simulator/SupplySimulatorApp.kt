package example.fakeuber.supply.simulator

import example.fakeuber.supply.simulator.actor.MainActor
import example.fakeuber.supply.simulator.actor.SupplierActor
import example.fakeuber.supply.simulator.kactor.kactorSystem
import `in`.example.fakeuber.supply.simulator.kactor.KActorRef.KActorRefOps.`!`
import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.*

var streets = mutableListOf("Rua do K", "Rua Soninho de Março", "Rua Sem Perdões", "Rua Meia Idade", "Rua do Patocórnio", "Rua do Deninho")
var history = mutableListOf("")

suspend fun main(): Unit = coroutineScope {
    val mainKActorRef = kactorSystem(MainActor.behavior)
    mainKActorRef `!` MainActor.Start
}
//fun main() {
//    val client = HttpClient {
//        install(WebSockets)
//    }
//    runBlocking {
//        client.webSocket(method = HttpMethod.Get, host = "localhost", port = 8080, path = "/chat") {
//
//            val startReceiveMessages = launch { receiveMessages() }
//            val startSendStreet = launch { sendStreet() }
//
//            startSendStreet.join()
//            startReceiveMessages.cancelAndJoin()
//        }
//    }
//    client.close()
//    println("Connection closed. Goodbye!")
//}

suspend fun DefaultClientWebSocketSession.receiveMessages() {
    try {
        for (message in incoming) {
            message as? Frame.Text ?: continue

            val message = message.readText().split(":")

            if (message.size != 2) continue

            val rua = message[0]
            val vindo = message[1] == "coming"

            if (vindo) streets.remove(rua) else streets.add(rua)
        }
    } catch (e: Exception) {
        println("Error while receiving" + e.localizedMessage)
    }
}

suspend fun DefaultClientWebSocketSession.sendStreet() {
    while (true) {
        var street = streets.random()

        while (history.last() == street){
            street = streets.random()
        }

        history.add(street)

        println("Sending street: $street")
        sendMessage("$street:vindo")

        val tempoParaProximaRua = (5..15).shuffled().first()
        delay((tempoParaProximaRua * 1000).toLong())

        sendMessage("$street:saindo")
    }
}

suspend fun DefaultClientWebSocketSession.sendMessage(message: String) {
    try {
        send(message)
    } catch (e: Exception) {
        println("Error while sending" + e.localizedMessage)
        return
    }
}