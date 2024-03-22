package com.study.alura.challenge.journeymiles.model.enums

enum class ErrorCodeEnum(val code: Int, val description: String) {
    TESTIMONIAL_NOT_FOUND(1001, "There is no testimonial with given id"),
    DESTINATION_NOT_FOUND(2001, "There is no destination with given id"),
    GENERIC_ERROR(9001, "Something went wrong with your request"),
    VALIDATION_ERROR(9002, "Invalid request")
}