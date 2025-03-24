package fake.uber.business

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BusinessApp

fun main(args: Array<String>) {
    runApplication<BusinessApp>(*args)
}