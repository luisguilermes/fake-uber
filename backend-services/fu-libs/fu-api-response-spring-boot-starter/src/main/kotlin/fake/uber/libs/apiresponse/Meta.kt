package fake.uber.libs.apiresponse

import java.time.Instant

data class Meta(
    val timestamp: Instant = Instant.now(),
    val requestId: String,
)
