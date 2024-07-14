package example.fakeuber.supply.simulator.actor

import example.fakeuber.supply.simulator.kactor.*
import kotlin.time.Duration.Companion.seconds

class SupplierActor(
    id: String,
    private val sound: String,
    private val referee: Actor<Int>,
) : AbstractActor<Int>(id) {
    override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
        println("$sound - $message")
        if (message >= 10) {
            referee.tell(message, sender)
        } else {
            sender.forEach(
                { actor: Actor<Int> -> actor.tell(message + 1, self()) },
                { referee.tell(message, sender) },
            )
        }
    }
}