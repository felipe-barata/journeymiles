package com.study.alura.challenge.journeymiles.security.dto

data class AuthenticationResponseDTO(
    val token: String,
    val refreshToken: String
)
