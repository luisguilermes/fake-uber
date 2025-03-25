package fake.uber.libs.apiresponse.util

import fake.uber.libs.apiresponse.Pagination

fun calculatePagination(
    page: Long,
    size: Long,
    totalElements: Long? = null,
): Pagination {
    if (totalElements == null) {
        return Pagination(page, size)
    }

    val totalPages = (totalElements / size).toInt() + if (totalElements % size > 0) 1 else 0
    return Pagination(page + 1, size, totalElements, totalPages)
}
