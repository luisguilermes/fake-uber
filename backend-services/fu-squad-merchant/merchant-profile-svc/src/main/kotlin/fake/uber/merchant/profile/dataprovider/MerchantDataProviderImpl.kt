package fake.uber.merchant.profile.dataprovider

import fake.uber.libs.apiresponse.Slice
import fake.uber.merchant.profile.dataprovider.postgres.MerchantRepository
import fake.uber.merchant.profile.domain.dataprovider.MerchantDataProvider
import fake.uber.merchant.profile.domain.entity.Merchant
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MerchantDataProviderImpl(
    private val restaurantRepository: MerchantRepository,
) : MerchantDataProvider {
    override fun getById(id: UUID): Merchant? = restaurantRepository.findById(id)

    override fun getNearby(
        lat: Double,
        lng: Double,
        radius: Int,
        page: Long,
        size: Long,
    ): Slice<Merchant> {
        val business = restaurantRepository.getNearby(lat, lng, radius, page, size)
        return Slice(
            content = if (business.size > size) business.dropLast(1) else business,
            page = page,
            size = size,
            hasNext = business.size > size,
        )
    }
}
