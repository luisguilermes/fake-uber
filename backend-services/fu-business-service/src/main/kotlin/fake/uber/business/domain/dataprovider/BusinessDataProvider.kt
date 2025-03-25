package fake.uber.business.domain.dataprovider

import fake.uber.business.domain.entity.Business
import java.util.UUID

interface BusinessDataProvider {
    fun getById(id: UUID): Business?

    fun getNearby(
        lat: Double,
        lng: Double,
        radius: Int,
        page: Long,
        size: Long,
    ): Set<Business>
}
