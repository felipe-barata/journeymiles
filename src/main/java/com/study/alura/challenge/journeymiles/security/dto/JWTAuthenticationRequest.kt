package com.study.alura.challenge.journeymiles.security.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class JWTAuthenticationRequest(
    @field:NotNull(message = "The email field is mandatory")
    @field:Email
    val email: String,
    @field:NotNull(message = "The password field is mandatory")
    @field:Size(min = 6, max = 12, message = "The password field must bet between 6 and 12 chars")
    val password: String
)
