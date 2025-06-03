plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "backend-services"

include(
    // Common libraries
    "fu-libs:fu-api-response-spring-boot-starter",
    "fu-libs:fu-kafka-avro-schemas",
    // Accounting services
    "fu-squad-accounting",
    // Delivery services
    "fu-squad-delivery",
    // Demand services
    "fu-squad-demand:customer-profile-svc",
    "fu-squad-demand:order-svc",
    // Logistics services
    "fu-squad-logistics",
    // Merchant services
    "fu-squad-merchant:merchant-profile-svc",
    // Ride-hailing services
    "fu-squad-ride-hailing:ride-order-svc",
    // Supply services
    "fu-squad-supply:availability-svc",
    "fu-squad-supply:driver-performance-svc",
    "fu-squad-supply:driver-profile-svc",
)
