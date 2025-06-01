package fake.uber.merchant.profile.domain.cmd

data class CreateBusinessRequest(
    val name: String,
    val lat: Double,
    val lng: Double,
)
