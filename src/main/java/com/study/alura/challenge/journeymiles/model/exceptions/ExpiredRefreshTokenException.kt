package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum
import org.springframework.http.HttpStatus

class ExpiredRefreshTokenException : DomainException(
    errorCodeEnum = ErrorCodeEnum.EXPIRED_REFRESH_TOKEN_ERROR,
    statusCode = HttpStatus.UNAUTHORIZED,
    message = "Expired refresh Token"
)