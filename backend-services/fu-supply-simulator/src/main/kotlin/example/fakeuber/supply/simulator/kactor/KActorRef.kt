package `in`.example.fakeuber.supply.simulator.kactor

import example.fakeuber.supply.simulator.actor.MainActor
import kotlinx.coroutines.channels.SendChannel

/**
 * A reference to an actor.
 *
 * @param T the type of messages the actor can receive
 */
class KActorRef<T> internal constructor(
    private val mailbox: SendChannel<T>,
) {
    suspend fun tell(msg: T) {
        mailbox.send(msg)
    }

    companion object KActorRefOps {
        suspend infix fun KActorRef<T>.`!`(msg: MainActor.Start) {
            tell(msg)
        }
    }
}