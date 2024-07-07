package example.fakeuber.supply.simulator.actor

import example.fakeuber.supply.simulator.kactor.*
import `in`.example.fakeuber.supply.simulator.kactor.KActorRef
import `in`.example.fakeuber.supply.simulator.kactor.KActorRef.KActorRefOps.`!`
import kotlin.time.Duration.Companion.seconds

object SupplierActor {
    sealed interface Command
    data class Increment(val by: Int) : Command
    data class GetValue(val replyTo: KActorRef<Int>) : Command

    data object TimerKey
    object Tick


    val behavior: KBehavior<Tick> = setup { _ ->
        withTimers { timers ->
            timers.startSingleTimer(TimerKey, Tick, 2.seconds)
            processTick(timers)
        }
    }

    private fun processTick(timers: TimerScheduler<Tick>): KBehavior<Tick> =
        receive { ctx, _ ->
            ctx.log.info("Another second passed.")
            processTick(timers)
        }

}