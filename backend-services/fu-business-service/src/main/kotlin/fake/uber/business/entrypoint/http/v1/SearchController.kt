package fake.uber.business.entrypoint.http.v1

import fake.uber.business.domain.dataprovider.BusinessDataProvider
import fake.uber.business.domain.entity.Business
import fake.uber.libs.apiresponse.Meta
import fake.uber.libs.apiresponse.PagedResponse
import fake.uber.libs.apiresponse.service.MessageService
import fake.uber.libs.apiresponse.util.calculatePagination
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/v1/search")
class SearchController(
    private val businessDataProvider: BusinessDataProvider,
    private val messageService: MessageService,
) {
    @GetMapping("/nearby")
    fun nearby(
        @RequestParam lat: Double,
        @RequestParam lng: Double,
        @RequestParam(required = false) radius: Int?,
        @RequestParam(required = false) page: Long?,
        @RequestParam(required = false) size: Long?,
        request: HttpServletRequest,
    ): PagedResponse<Business> {
        val requestId = UUID.randomUUID().toString()
        val effectiveRadius = radius ?: 5
        val effectivePage = page ?: 0
        val effectiveSize = size ?: 10
        val businesses = businessDataProvider.getNearby(lat, lng, effectiveRadius, effectivePage, effectiveSize)
        val pagination = calculatePagination(effectivePage, effectiveSize, null)

        val message = messageService.getMessage("response.success", request)

        return PagedResponse.success(
            message = message,
            data = businesses,
            pagination = pagination,
            meta = Meta(requestId = requestId),
        )
    }
}
