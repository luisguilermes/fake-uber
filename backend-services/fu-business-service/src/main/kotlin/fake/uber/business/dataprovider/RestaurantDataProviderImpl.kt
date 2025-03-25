package fake.uber.business.dataprovider

import fake.uber.business.dataprovider.postgres.RestaurantRepository
import fake.uber.business.domain.dataprovider.BusinessDataProvider
import fake.uber.business.domain.entity.Business
import fake.uber.libs.apiresponse.Slice
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
    ): Slice<Business> {
        val business = restaurantRepository.getNearby(lat, lng, radius, page, size)
        return Slice(
            content = if (business.size > size) business.dropLast(1) else business,
            page = page,
            size = size,
            hasNext = business.size > size,
        )
    }
}
