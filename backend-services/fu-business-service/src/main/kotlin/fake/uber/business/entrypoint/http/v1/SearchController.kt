package fake.uber.business.entrypoint.http.v1

import fake.uber.business.domain.dataprovider.BusinessDataProvider
import fake.uber.business.entrypoint.http.v1.dto.PaginatedResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/search")
class SearchController(
    private val businessDataProvider: BusinessDataProvider
) {
    @GetMapping("/nearby")
    fun nearby(
        @RequestParam lat: Double,
        @RequestParam lng: Double,
        @RequestParam(required = false) radius: Int?,
        @RequestParam(required = false) page: Int?,
        @RequestParam(required = false) size: Int?,
    ): ResponseEntity<PaginatedResponse> {
        val effectiveRadius = radius ?: 5
        val effectivePage = page ?: 0
        val effectiveSize = size ?: 10

        val businesses = businessDataProvider.getNearby(lat, lng, effectiveRadius, effectivePage, effectiveSize)
        return ResponseEntity.ok(PaginatedResponse(effectivePage, effectiveSize, businesses.size, businesses))
    }
}
