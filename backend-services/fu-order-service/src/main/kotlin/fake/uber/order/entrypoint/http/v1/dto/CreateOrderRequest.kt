package fake.uber.order.entrypoint.http.v1.dto

import java.util.UUID

data class CreateOrderRequest(
    val consumerId: UUID,
    val businessId: UUID,
    val items: List<CreateOrderItemRequest>,
    val payment: CreateOrderPaymentRequest,
    val delivery: CreateOrderDeliveryRequest
)

data class CreateOrderItemRequest(
    val productId: UUID,
    val quantity: Int,
)

data class CreateOrderPaymentRequest(
    val method: String,
)

data class CreateOrderDeliveryRequest(
    val consumerAddressId: UUID,
    val businessAddressId: UUID
)