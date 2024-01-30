package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum
import org.springframework.http.HttpStatus

class TestimonialNotFoundException(id: Long): DomainException(
        code = ErrorCodeEnum.TESTIMONIAL_NOT_FOUND.code,
        statusCode = HttpStatus.NOT_FOUND,
        message = "There is no testimonial with given id: $id"
)