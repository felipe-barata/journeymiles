package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum

class AuthenticationNotPresentException() : BadRequestException(
    errorCodeEnum = ErrorCodeEnum.AUTHENTICATION_NOT_PRESENT_ERROR,
    message = "Authentication Token not present"
)