package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum
import org.springframework.http.HttpStatus

open class DomainException(
    val errorCodeEnum: ErrorCodeEnum,
    val statusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val details: List<String> = emptyList(),
    override val message: String? = null,
    exception: Exception? = null
) : RuntimeException(message, exception)