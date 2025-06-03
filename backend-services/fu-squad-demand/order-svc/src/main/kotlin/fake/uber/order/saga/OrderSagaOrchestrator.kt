package fake.uber.order.saga

import fake.uber.libs.avro_schemas.accounting.PaymentCompletedEvent
import fake.uber.libs.avro_schemas.accounting.PaymentRequestedEvent
import fake.uber.libs.avro_schemas.demand.MerchantOrderCreatedEvent
import fake.uber.libs.avro_schemas.demand.RideHailingOrderCreatedEvent
import fake.uber.order.domain.dataprovider.OrderRepository
import fake.uber.order.domain.model.Order
import fake.uber.order.domain.model.OrderStatus
import fake.uber.order.entrypoint.http.v1.dto.OrderType
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OrderSagaOrchestrator(
    private val orderRepository: OrderRepository,
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) {
    fun startSaga(order: Order) {
        orderRepository.save(order.copy(status = OrderStatus.CREATED))
        val event = PaymentRequestedEvent(order.id)
        kafkaTemplate.send("payment-requested", UUID.randomUUID().toString(), event).get()
    }

    @KafkaListener(topics = ["payment-completed"])
    fun onPaymentCompleted(event: PaymentCompletedEvent) {
        val order =
            orderRepository.findById(event.orderId)
                ?: throw IllegalStateException("Order not found for ID: ${event.orderId}")
        orderRepository.save(order.copy(status = OrderStatus.PAYMENT_CONFIRMED))
        when (order.type) {
            OrderType.MERCHANT -> {
                val merchantOrder = MerchantOrderCreatedEvent(order.id)
                kafkaTemplate.send("merchant-order-created", UUID.randomUUID().toString(), merchantOrder).get()
            }
            OrderType.PASSENGER -> {
                val passengerOrder = RideHailingOrderCreatedEvent(order.id)
                kafkaTemplate.send("passenger-order-created", UUID.randomUUID().toString(), passengerOrder).get()
            }
        }
    }
}
