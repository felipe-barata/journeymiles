package com.study.alura.challenge.journeymiles.config.handlers

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum
import com.study.alura.challenge.journeymiles.model.exceptions.DomainException
import java.io.Serializable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun handleGenericException(exception: Exception) = handleExceptions(
        DomainException(
            errorCodeEnum = ErrorCodeEnum.GENERIC_ERROR,
            exception = exception
        )
    )

    @ExceptionHandler
    fun handleDomainException(exception: DomainException) = handleExceptions(
        exception
    )

    @ExceptionHandler
    fun handleValidationException(exception: MethodArgumentNotValidException) = handleExceptions(
        DomainException(
            errorCodeEnum = ErrorCodeEnum.VALIDATION_ERROR,
            statusCode = HttpStatus.BAD_REQUEST,
            details = exception.allErrors.map {
                "${(it as FieldError).field} - ${it.defaultMessage}"
            }
        )
    )

    private fun handleExceptions(domainException: DomainException) = ResponseEntity.status(domainException.statusCode)
        .body(
            ErrorResponseDTO(
                errorCode = domainException.errorCodeEnum.code,
                errorMessage = domainException.message ?: domainException.errorCodeEnum.description,
                errorDetails = domainException.details
            )
        )

    data class ErrorResponseDTO(
        val errorCode: Int,
        val errorMessage: String,
        val errorDetails: List<String>?
    ) : Serializable

}
