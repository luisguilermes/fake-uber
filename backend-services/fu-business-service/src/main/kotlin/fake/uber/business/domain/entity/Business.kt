package fake.uber.business.domain.entity

import java.util.UUID

data class Business(
    val id: UUID,
    val name: String,
    val lat: Double,
    val lng: Double,
)