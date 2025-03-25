package fake.uber.business.dataprovider

import fake.uber.business.dataprovider.postgres.RestaurantRepository
import fake.uber.business.domain.dataprovider.BusinessDataProvider
import fake.uber.business.domain.entity.Business
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RestaurantDataProviderImpl(
    private val restaurantRepository: RestaurantRepository,
) : BusinessDataProvider {
    override fun getById(id: UUID): Business? = restaurantRepository.findById(id)

    override fun getNearby(
        lat: Double,
        lng: Double,
        radius: Int,
        page: Long,
        size: Long,
    ): Set<Business> = restaurantRepository.getNearby(lat, lng, radius, page, size)
}
