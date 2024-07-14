//package example.fakeuber.supply.simulator.actor
//
//import example.fakeuber.supply.simulator.kactor.KBehavior
//import example.fakeuber.supply.simulator.kactor.receive
//import example.fakeuber.supply.simulator.kactor.same
//import example.fakeuber.supply.simulator.kactor.spawn
//import kotlinx.coroutines.delay
//
//object MainActor {
//    object Start
//
//    val suppliers = listOf(
//        "6ceb49b0-0aea-4f75-9a58-64a3c49d5b73",
////        "3ef0748d-fee9-4b0a-9fe6-4b55fedc9672",
////        "2b366c46-e229-4bbd-a5fc-9eea19741d93",
////        "730e8d56-b247-42a9-aca5-a6f1665005ba"
//    )
//
//    val behavior: KBehavior<Start> =  receive { ctx, _ ->
//        suppliers.forEach {
//            it to ctx.spawn("supplier-$it", SupplierActor.behavior)
//            delay(1 * 500L)
//        }
//        same()
//    }
//}
//
