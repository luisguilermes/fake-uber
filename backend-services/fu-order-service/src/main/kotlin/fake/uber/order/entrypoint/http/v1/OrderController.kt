package fake.uber.order.entrypoint.http.v1

import fake.uber.order.entrypoint.http.v1.dto.CreateOrderRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/consumer/orders")
class OrderController {
    @PostMapping("/consumer")
    fun createConsumerOrder(@RequestBody dto: CreateOrderRequest) {

    }
}