package com.study.alura.challenge.journeymiles.model.enums

enum class ErrorCodeEnum(val code: Int, val description: String) {
    TESTIMONIAL_NOT_FOUND_ERROR(1001, "There is no testimonial with given id"),
    USER_NOT_RELATED_TO_TESTIMONIAL_ERROR(1002, "There user is not allowed to edit/delete this testimonial"),
    DESTINATION_NOT_FOUND_ERROR(2001, "There is no destination with given id"),
    USER_NOT_FOUND_ERROR(3001, "There is no user with given id"),
    UNSUPPORTED_PICTURE_TYPE_ERROR(4001, "There is no picture with given type"),
    PICTURE_NOT_FOUND_ERROR(8001, "There is no picture with given parameters"),
    GENERIC_ERROR(9001, "Something went wrong with your request"),
    VALIDATION_ERROR(9002, "Invalid request")
}