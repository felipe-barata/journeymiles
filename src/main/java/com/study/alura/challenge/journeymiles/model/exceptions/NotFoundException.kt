package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum
import org.springframework.http.HttpStatus

open class NotFoundException(message: String, errorCodeEnum: ErrorCodeEnum) : DomainException(
    code = errorCodeEnum.code,
    statusCode = HttpStatus.NOT_FOUND,
    message = message
)
