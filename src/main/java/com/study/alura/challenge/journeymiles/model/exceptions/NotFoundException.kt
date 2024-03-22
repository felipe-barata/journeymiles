package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum
import org.springframework.http.HttpStatus

open class NotFoundException(message: String, errorCodeEnum: ErrorCodeEnum) : DomainException(
    errorCodeEnum = errorCodeEnum,
    statusCode = HttpStatus.NOT_FOUND,
    message = message
)
