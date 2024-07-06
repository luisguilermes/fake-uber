package example.fakeuber.supply.simulator.actor

import example.fakeuber.supply.simulator.kactor.*
import `in`.example.fakeuber.supply.simulator.kactor.KActorRef
import `in`.example.fakeuber.supply.simulator.kactor.KActorRef.KActorRefOps.`!`

object SupplierActor {
    object ReplyReceived

    data class SayHello(val name: String, val replyTo: KActorRef<ReplyReceived>)

    val behavior: KBehavior<SayHello> =
        receive { ctx, msg ->
            ctx.log.info("Hello ${msg.name}!")
            msg.replyTo `!` ReplyReceived
            same()
        }
}
