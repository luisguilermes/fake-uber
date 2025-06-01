package fake.uber.merchant.profile.domain.service

import fake.uber.merchant.profile.domain.entity.Merchant
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MerchantService(
    private val merchantDataProvider: fake.uber.merchant.profile.domain.dataprovider.MerchantDataProvider,
) {
    fun getById(id: UUID): Merchant = merchantDataProvider.getById(id) ?: throw NoSuchElementException("Merchant not found")
}
