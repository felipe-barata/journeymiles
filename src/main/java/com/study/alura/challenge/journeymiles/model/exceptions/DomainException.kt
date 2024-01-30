package com.study.alura.challenge.journeymiles.model.exceptions

import org.springframework.http.HttpStatus

open class DomainException(
        val code: Int,
        val statusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
        val details: List<String> = emptyList(),
        override val message: String,
        exception: Exception? = null
) : RuntimeException(message, exception)