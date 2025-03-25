package fake.uber.libs.apiresponse

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: T? = null,
    val errors: List<ApiError>? = null,
    val meta: Meta? = null,
) {
    companion object {
        fun <T> success(
            message: String,
            data: T,
            meta: Meta? = null,
        ): ApiResponse<T> {
            return ApiResponse("success", message, data, null, meta)
        }

        fun error(
            message: String,
            errors: List<ApiError>,
        ): ApiResponse<Nothing> {
            return ApiResponse("error", message, null, errors, null)
        }
    }
}
