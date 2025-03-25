package fake.uber.libs.apiresponse

open class Slice<T>(
    val content: List<T>,
    val page: Long,
    val size: Long,
    val hasNext: Boolean,
)
