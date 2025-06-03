package fake.uber.order.domain.dataprovider

import fake.uber.order.domain.model.Order
import java.util.UUID

interface OrderRepository {
    fun save(order: Order)

    fun findById(id: UUID): Order?
}
