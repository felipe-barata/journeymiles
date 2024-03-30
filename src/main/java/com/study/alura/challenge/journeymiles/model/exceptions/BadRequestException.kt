package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum
import org.springframework.http.HttpStatus

open class BadRequestException(message: String, errorCodeEnum: ErrorCodeEnum) : DomainException(
    errorCodeEnum = errorCodeEnum,
    statusCode = HttpStatus.BAD_REQUEST,
    message = message
)
