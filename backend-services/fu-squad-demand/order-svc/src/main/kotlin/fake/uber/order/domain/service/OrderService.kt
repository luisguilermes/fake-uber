package fake.uber.order.domain.service

import com.fasterxml.jackson.databind.ObjectMapper
import fake.uber.order.entrypoint.http.v1.dto.CreateOrderRequest
import fake.uber.order.entrypoint.http.v1.dto.MerchantOrderPayload
import fake.uber.order.entrypoint.http.v1.dto.OrderType
import fake.uber.order.entrypoint.http.v1.dto.PassengerOrderPayload
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderService(
    private val objectMapper: ObjectMapper,
    private val merchantOrderService: MerchantOrderService,
    private val passengerOrderService: PassengerOrderService,
) {
    fun createOrder(request: CreateOrderRequest): UUID =
        when (request.orderType) {
            OrderType.MERCHANT -> {
                val payload = objectMapper.convertValue(request.payload, MerchantOrderPayload::class.java)
                merchantOrderService.handleOrder(request.customerId, payload)
            }
            OrderType.PASSENGER -> {
                val payload = objectMapper.convertValue(request.payload, PassengerOrderPayload::class.java)
                passengerOrderService.handleOrder(request.customerId, payload)
            }
        }
}
