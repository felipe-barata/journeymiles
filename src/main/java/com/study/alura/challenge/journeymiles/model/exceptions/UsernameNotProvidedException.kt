package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum

class UsernameNotProvidedException() : NotFoundException(
    errorCodeEnum = ErrorCodeEnum.USERNAME_NOT_PROVIDED_ERROR,
    message = "Username not provided"
)