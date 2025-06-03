package fake.uber.order.domain.service

import fake.uber.order.entrypoint.http.v1.dto.MerchantOrderPayload
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MerchantOrderService {
    fun handleOrder(
        customerId: String,
        payload: MerchantOrderPayload,
    ): UUID = UUID.randomUUID()
}
