package fake.uber.order.entrypoint.http.v1.dto

data class CreateOrderRequest(
    val orderType: OrderType,
    val customerId: String,
    val paymentMethod: String,
    val deliveryAddress: DeliveryAddress?,
    val payload: Map<String, Any>,
)

enum class OrderType {
    MERCHANT,
    PASSENGER,
}

data class DeliveryAddress(
    val street: String,
    val number: String?,
    val neighborhood: String?,
    val city: String,
    val state: String,
    val postalCode: String?,
    val reference: String?,
    val location: GeoPoint,
)

data class MerchantOrderPayload(
    val merchantId: String,
    val items: List<FoodItem>,
    val instructions: String?,
)

data class FoodItem(
    val productId: String,
    val quantity: Int,
)

data class PassengerOrderPayload(
    val pickup: GeoPoint,
    val dropoff: GeoPoint,
    val passengerCount: Int,
    val rideOption: String,
)

data class PackageOrderPayload(
    val pickup: GeoPoint,
    val dropoff: GeoPoint,
    val packageDetails: PackageDetails,
    val insuranceRequired: Boolean,
)

data class GeoPoint(
    val lat: Double,
    val lng: Double,
)

data class PackageDetails(
    val weight: Double,
    val dimensions: Dimensions,
    val type: String,
)

data class Dimensions(
    val height: Double,
    val width: Double,
    val depth: Double,
)
