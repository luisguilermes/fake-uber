package fake.uber.order

import fake.uber.libs.apiresponse.config.ApiResponseAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(ApiResponseAutoConfiguration::class)
class OrderApp

fun main(args: Array<String>) {
    runApplication<OrderApp>(*args)
}
