package com.study.alura.challenge.journeymiles.security.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RefreshTokenRequest(
    @field:NotNull(message = "The token field is mandatory")
    @field:NotBlank(message = "The token field is mandatory")
    val token: String
)
