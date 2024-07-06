package example.fakeuber.supply.simulator.actor

import example.fakeuber.supply.simulator.kactor.*
import `in`.example.fakeuber.supply.simulator.kactor.KActorRef.KActorRefOps.`!`
import kotlin.time.Duration.Companion.seconds

object MainActor {
    object Start

    fun behavior(): KBehavior<Start> = receive { ctx, _ ->
        for (j in 1..10) {
            for (i in 0..1) {
                val supplierActor = ctx.spawn("supplier ${j+i}", blocking(FileReader.behavior()))
                supplierActor `!` FileReader.Tick
            }
        }
        same()
    }
}

object FileReader {

    object TimerKey
    object Tick

    fun behavior(): KBehavior<Tick> = withTimers { timers ->
            timers.startSingleTimer(TimerKey, Tick, 10.seconds)
            processTick(0, timers)
        }

    private fun processTick(counter: Int, timers: TimerScheduler<Tick>): KBehavior<Tick> =
        receive { ctx, _ ->
            ctx.log.info("Another second passed")
            if (counter == 10) {
                timers.cancel(TimerKey)
                stopped()
            } else {
                processTick(counter + 1, timers)
            }
        }
}