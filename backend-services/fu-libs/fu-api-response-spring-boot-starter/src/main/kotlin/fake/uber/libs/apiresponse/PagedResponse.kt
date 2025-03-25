package fake.uber.libs.apiresponse

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PagedResponse<T>(
    val status: String,
    val message: String,
    val data: Set<T>,
    val pagination: Pagination,
    val meta: Meta? = null,
) {
    companion object {
        fun <T> success(
            message: String,
            data: Set<T>,
            pagination: Pagination,
            meta: Meta? = null,
        ): PagedResponse<T> {
            return PagedResponse("success", message, data, pagination, meta)
        }
    }
}
