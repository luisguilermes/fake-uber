package example.fakeuber.supply.simulator.actor

import example.fakeuber.supply.simulator.kactor.*
import `in`.example.fakeuber.supply.simulator.kactor.KActorRef.KActorRefOps.`!`
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

object MainActor {
    object Start
    val suppliers = listOf(
        "6ceb49b0-0aea-4f75-9a58-64a3c49d5b73",
        "3ef0748d-fee9-4b0a-9fe6-4b55fedc9672"
    )

    fun behavior(): KBehavior<Start> = receive { ctx, _ ->
        val suppliersActors = suppliers.map {
            it to ctx.spawn("supplier-$it", blocking(SupplierActor.behavior))
        }

        for ((id, actor) in suppliersActors) {
//            actor `!` SupplierActor.Create(id)
            delay(1 * 500L)
        }


//        for (supplier in suppliers) {
//            val actorName = "supplier-$supplier"
//            val supplierActor = ctx.spawn(actorName, blocking(SupplierActor.behavior))
////            supplierActor `!` FileReader.Tick
//            supplierActor `!` SupplierActor.Create(supplier)
////            delay(1 * 500L)
//        }
        same()
    }
}

object FileReader {

    object TimerKey
    object Tick

    fun behavior(): KBehavior<Tick> = withTimers { timers ->
            timers.startSingleTimer(TimerKey, Tick, 2.seconds)
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