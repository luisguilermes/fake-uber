package fake.uber.libs.apiresponse.service

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.MessageSource
import org.springframework.web.servlet.support.RequestContextUtils
import java.util.Locale

class MessageService(private val messageSource: MessageSource) {
    fun getMessage(
        key: String,
        request: HttpServletRequest,
        vararg args: Any,
    ): String {
        val locale: Locale = RequestContextUtils.getLocale(request)
        return messageSource.getMessage(key, args, locale)
    }
}
