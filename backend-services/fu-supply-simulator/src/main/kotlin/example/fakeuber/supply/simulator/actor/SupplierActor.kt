package example.fakeuber.supply.simulator.actor

import example.fakeuber.supply.simulator.kactor.*
import kotlin.time.Duration.Companion.seconds

object SupplierActor {
    sealed interface Command
    data class StartCommand(val id: String) : Command

    val behavior: KBehavior<Unit> =
        setup { ctx, _ ->
            ctx.log.info("Hello!")
            stopped()
        }

//    fun behavior(): KBehavior<Tick> = withTimers { timers ->
//        timers.startSingleTimer(
//            TimerKey,
//            Tick, 2.seconds)
//        processTick(0, timers)
//    }

//    private fun processTick(counter: Int, timers: TimerScheduler<Tick>): KBehavior<Tick> =
//        receive { ctx, _ ->
//            ctx.log.info("Another second passed")
//            if (counter == 10) {
//                timers.cancel(example.fakeuber.supply.simulator.actor.FileReader.TimerKey)
//                stopped()
//            } else {
//                processTick(counter + 1, timers)
//            }
//        }
}
