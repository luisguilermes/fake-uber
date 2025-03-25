package fake.uber.libs.apiresponse.config

import fake.uber.libs.apiresponse.ApiError
import fake.uber.libs.apiresponse.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandlerConfig {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Nothing>> {
        val errors = ex.bindingResult.fieldErrors.map { ApiError(it.field, it.defaultMessage ?: "Erro desconhecido") }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ApiResponse.error("Erro de validação.", errors),
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ApiResponse.error("Erro interno do servidor.", listOf(ApiError("internal", ex.message ?: "Erro desconhecido"))),
        )
    }
}
