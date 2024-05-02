package com.study.alura.challenge.journeymiles.security.service

import com.study.alura.challenge.journeymiles.security.dto.JWTAuthenticationRequest
import com.study.alura.challenge.journeymiles.security.dto.RefreshTokenRequest
import com.study.alura.challenge.journeymiles.security.dto.AuthenticationResponseDTO
import com.study.alura.challenge.journeymiles.security.dto.TokenResponseDTO

interface AuthenticationService {

    fun authenticate(request: JWTAuthenticationRequest): AuthenticationResponseDTO

    fun refreshToken(refreshTokenRequest: RefreshTokenRequest): TokenResponseDTO
}