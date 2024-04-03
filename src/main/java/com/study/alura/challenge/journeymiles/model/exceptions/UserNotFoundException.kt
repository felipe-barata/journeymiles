package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum

class UserNotFoundException(id: Long) : NotFoundException(
    errorCodeEnum = ErrorCodeEnum.USER_NOT_FOUND_ERROR,
    message = "There is no user with given id: $id"
)