package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum
import org.springframework.http.HttpStatus

class PictureAlreadyPresentException(id: Long, type: String) : DomainException(
    errorCodeEnum = ErrorCodeEnum.PICTURE_ALREADY_PRESENT_ERROR,
    message = "There is no picture with given id: $id and type: $type",
    statusCode = HttpStatus.CONFLICT
)