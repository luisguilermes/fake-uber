package fake.uber.libs.apiresponse

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PagedResponse<T>(
    val status: String,
    val message: String,
    val data: List<T>,
    val pageable: Pageable,
    val meta: Meta? = null,
) {
    companion object {
        fun <T> success(
            message: String,
            data: List<T>,
            pageable: Pageable,
            meta: Meta? = null,
        ): PagedResponse<T> = PagedResponse("success", message, data, pageable, meta)
    }
}
