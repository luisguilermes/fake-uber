package fake.uber.consumer

import fake.uber.libs.apiresponse.config.ApiResponseAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(ApiResponseAutoConfiguration::class)
class ConsumerApp

fun main(args: Array<String>) {
    runApplication<ConsumerApp>(*args)
}
