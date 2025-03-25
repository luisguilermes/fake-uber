package fake.uber.libs.apiresponse.util

import fake.uber.libs.apiresponse.Pageable

fun calculatePagination(
    page: Long,
    size: Long,
    totalElements: Long? = null,
): Pageable {
    val totalPages = totalElements?.let { ((totalElements / size) + if (totalElements % size > 0) 1 else 0) } ?: 0
    return Pageable(page = page + 1, size = size, totalElements = totalElements, totalPages = totalPages)
}
