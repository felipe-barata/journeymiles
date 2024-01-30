package com.study.alura.challenge.journeymiles.model.enums

enum class ErrorCodeEnum(val code: Int, val description: String) {
    TESTIMONIAL_NOT_FOUND(1001, "There is no testimonial with given id")
}