package fake.uber.business

import fake.uber.libs.apiresponse.config.ApiResponseAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(ApiResponseAutoConfiguration::class)
class BusinessApp

fun main(args: Array<String>) {
    runApplication<BusinessApp>(*args)
}
