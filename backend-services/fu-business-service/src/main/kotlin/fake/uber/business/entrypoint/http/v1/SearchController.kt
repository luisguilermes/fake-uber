package fake.uber.business.entrypoint.http.v1

import fake.uber.business.domain.dataprovider.BusinessDataProvider
import fake.uber.business.domain.entity.Business
import fake.uber.libs.apiresponse.Meta
import fake.uber.libs.apiresponse.Pageable
import fake.uber.libs.apiresponse.PagedResponse
import fake.uber.libs.apiresponse.service.MessageService
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
        @RequestParam(required = false, defaultValue = "10") radius: Int,
        @RequestParam(required = false, defaultValue = "0") page: Long,
        @RequestParam(required = false, defaultValue = "10") size: Long,
        request: HttpServletRequest,
    ): PagedResponse<Business> {
        val meta = Meta(requestId = UUID.randomUUID().toString())
        val slicedBusinesses = businessDataProvider.getNearby(lat, lng, radius, page, size)
        val message = messageService.getMessage("response.success", request)

        return PagedResponse.success(
            message = message,
            data = slicedBusinesses.content,
            pageable = Pageable.of(slicedBusinesses),
            meta = meta,
        )
    }
}
