package com.study.alura.challenge.journeymiles.model.exceptions

import com.study.alura.challenge.journeymiles.model.enums.ErrorCodeEnum

class InvalidUserForTestimonialException(userId: Long, testimonialId: Long) : BadRequestException(
    errorCodeEnum = ErrorCodeEnum.USER_NOT_RELATED_TO_TESTIMONIAL_ERROR,
    message = "The user with id: $userId is not allowed to manage the testimonial with id: $testimonialId"
)