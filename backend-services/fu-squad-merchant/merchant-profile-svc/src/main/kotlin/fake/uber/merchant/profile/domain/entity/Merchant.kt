package fake.uber.merchant.profile.domain.entity

import java.util.UUID

data class Merchant(
    val id: UUID,
    val name: String,
    val lat: Double,
    val lng: Double,
)
