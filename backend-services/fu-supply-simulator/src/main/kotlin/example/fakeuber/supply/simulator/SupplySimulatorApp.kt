package example.fakeuber.supply.simulator

import example.fakeuber.supply.simulator.actor.SupplierActor
import example.fakeuber.supply.simulator.kactor.AbstractActor
import example.fakeuber.supply.simulator.kactor.Actor
import kotlinx.coroutines.sync.Semaphore
import example.fakeuber.supply.simulator.kactor.Result

private val semaphore = Semaphore(1)



suspend fun main(args: Array<String>) {
    val referee = object : AbstractActor<Int>("Referee") {
        override fun onReceive(message: Int, sender: Result<Actor<Int>>) {
            println("Game ended after $message shots")
            semaphore.release()
        }
    }

    val player1 = SupplierActor("Player 1", "Ping", referee)
    val player2 = SupplierActor("Player 2", "Pong", referee)
    semaphore.acquire()
    player1.tell(0, Result(player2))
    semaphore.acquire()

}