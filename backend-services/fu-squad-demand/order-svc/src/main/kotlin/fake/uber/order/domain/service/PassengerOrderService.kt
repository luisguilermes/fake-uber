package fake.uber.order.domain.service

import fake.uber.order.entrypoint.http.v1.dto.PassengerOrderPayload
import org.springframework.stereotype.Service
import java.util.*

@Service
class PassengerOrderService {
    fun handleOrder(
        customerId: String,
        payload: PassengerOrderPayload,
    ): UUID = UUID.randomUUID()
}
