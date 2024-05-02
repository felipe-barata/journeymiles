package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum

class UserNotFoundByEmailException(email: String?) : NotFoundException(
    errorCodeEnum = ErrorCodeEnum.USER_NOT_FOUND_BY_EMAIL_ERROR,
    message = "There is no user with given email: $email"
)