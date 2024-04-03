package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum

class TestimonialNotFoundException(id: Long) : NotFoundException(
    errorCodeEnum = ErrorCodeEnum.TESTIMONIAL_NOT_FOUND_ERROR,
    message = "There is no testimonial with given id: $id"
)