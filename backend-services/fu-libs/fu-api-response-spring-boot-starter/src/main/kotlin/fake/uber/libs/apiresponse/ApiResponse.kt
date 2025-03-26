package fake.uber.libs.apiresponse

data class ApiResponse<T>(
    val status: String,
    val message: String,
    val content: T? = null,
    val errors: List<ApiError>? = null,
    val meta: Meta? = null,
) {
    companion object {
        fun <T> success(
            message: String,
            data: T,
            meta: Meta? = null,
        ): ApiResponse<T> = ApiResponse("success", message, data, null, meta)

        fun error(
            message: String,
            errors: List<ApiError>,
        ): ApiResponse<Nothing> = ApiResponse("error", message, null, errors, null)
    }
}
