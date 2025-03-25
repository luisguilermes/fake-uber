package fake.uber.libs.apiresponse.config

import fake.uber.libs.apiresponse.service.MessageService
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.Locale

@AutoConfiguration
class ApiResponseAutoConfiguration {
    @Bean
    fun globalExceptionHandler() = GlobalExceptionHandlerConfig()

    @Bean
    fun localeResolver(): LocaleResolver {
        val localeResolver = AcceptHeaderLocaleResolver()
        localeResolver.setDefaultLocale(Locale.US)
        return localeResolver
    }

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }

    @Bean
    fun messageService(messageSource: MessageSource): MessageService {
        return MessageService(messageSource)
    }
}
