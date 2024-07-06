package `in`.example.fakeuber.supply.simulator.kactor

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
        suspend infix fun <T> KActorRef<T>.`!`(msg: T) {
            tell(msg)
        }
    }
}