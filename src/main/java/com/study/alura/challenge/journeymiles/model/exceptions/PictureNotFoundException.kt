package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum

class PictureNotFoundException(id: Long, type: String) : NotFoundException(
    errorCodeEnum = ErrorCodeEnum.PICTURE_NOT_FOUND_ERROR,
    message = "There is no picture with given id: $id and type: $type"
)