package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum

class DestinationNotFoundException(id: Long) : NotFoundException(
    errorCodeEnum = ErrorCodeEnum.DESTINATION_NOT_FOUND_ERROR,
    message = "There is no destination with given id: $id"
)