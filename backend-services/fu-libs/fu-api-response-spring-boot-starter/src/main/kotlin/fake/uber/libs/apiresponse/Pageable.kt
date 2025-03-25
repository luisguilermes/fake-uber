package fake.uber.libs.apiresponse

data class Pageable(
    val page: Long,
    val size: Long,
    val hasNext: Boolean? = null,
    val totalElements: Long? = null,
    val totalPages: Long? = null,
) {
    companion object {
        fun <T> of(slice: Slice<T>): Pageable =
            Pageable(
                page = slice.page,
                size = slice.size,
                hasNext = slice.hasNext,
            )
    }
}
