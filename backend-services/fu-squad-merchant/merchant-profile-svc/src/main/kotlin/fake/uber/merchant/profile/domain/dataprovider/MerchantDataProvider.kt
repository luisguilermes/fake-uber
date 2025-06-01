package fake.uber.merchant.profile.domain.dataprovider

import fake.uber.libs.apiresponse.Slice
import fake.uber.merchant.profile.domain.entity.Merchant
import java.util.UUID

interface MerchantDataProvider {
    fun getById(id: UUID): Merchant?

    fun getNearby(
        lat: Double,
        lng: Double,
        radius: Int,
        page: Long,
        size: Long,
    ): Slice<Merchant>
}
