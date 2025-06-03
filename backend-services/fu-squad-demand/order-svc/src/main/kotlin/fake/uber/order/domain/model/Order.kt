package fake.uber.order.domain.model

import fake.uber.order.entrypoint.http.v1.dto.OrderType
import java.util.UUID

data class Order(
    val id: UUID,
    val type: OrderType,
    val status: OrderStatus = OrderStatus.CREATED,
)

enum class OrderStatus {
    CREATED,
    PAYMENT_CONFIRMED,
    ACCEPTED,
    REJECTED,
    COMPLETED,
    CANCELLED,
}
