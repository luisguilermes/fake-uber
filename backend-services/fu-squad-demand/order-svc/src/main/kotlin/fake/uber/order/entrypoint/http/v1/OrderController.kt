package fake.uber.order.entrypoint.http.v1

import fake.uber.order.domain.service.OrderService
import fake.uber.order.entrypoint.http.v1.dto.CreateOrderRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping
    fun createOrder(
        @RequestBody request: CreateOrderRequest,
    ): ResponseEntity<CreateOrderResponse> {
        val orderId = orderService.createOrder(request)
        return ResponseEntity.ok(CreateOrderResponse(orderId))
    }
}

data class CreateOrderResponse(
    val orderId: UUID,
)
