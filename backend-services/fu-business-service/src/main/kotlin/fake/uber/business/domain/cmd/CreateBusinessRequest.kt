package fake.uber.business.domain.cmd

data class CreateBusinessRequest(
    val name: String,
    val lat: Double,
    val lng: Double,
)
