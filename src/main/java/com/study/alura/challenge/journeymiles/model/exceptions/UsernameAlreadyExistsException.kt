package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum

class UsernameAlreadyExistsException(email: String?) : NotFoundException(
    errorCodeEnum = ErrorCodeEnum.USERNAME_ALREADY_EXISTS_ERROR,
    message = "There is already an user with given email: $email"
)