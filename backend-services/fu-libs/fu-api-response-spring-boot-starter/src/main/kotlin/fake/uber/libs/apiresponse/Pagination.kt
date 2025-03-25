package fake.uber.libs.apiresponse

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Pagination(
    val page: Long,
    val size: Long,
    val totalElements: Long? = null,
    val totalPages: Int? = null,
)
