package fake.uber.business.entrypoint.http.v1.dto

data class PaginatedResponse(
    val page: Int,
    val size: Int,
    val total: Int,
    val content: Set<Any>,
)