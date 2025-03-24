package fake.uber.business.domain.service

import fake.uber.business.domain.dataprovider.BusinessDataProvider
import fake.uber.business.domain.entity.Business
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BusinessService(
    private val businessDataProvider: BusinessDataProvider,
) {
    fun getById(id: UUID): Business = businessDataProvider.getById(id) ?: throw NoSuchElementException("Business not found")
}
