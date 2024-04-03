package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum

class UnsupportedPictureTypeException(type: String) : BadRequestException(
    errorCodeEnum = ErrorCodeEnum.UNSUPPORTED_PICTURE_TYPE_ERROR,
    message = "There is no picture with given type: $type"
)