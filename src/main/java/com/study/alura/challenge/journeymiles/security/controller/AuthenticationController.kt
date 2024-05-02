package com.study.alura.challenge.journeymiles.security.controller

import com.study.alura.challenge.journeymiles.security.dto.JWTAuthenticationRequest
import com.study.alura.challenge.journeymiles.security.dto.RefreshTokenRequest
import com.study.alura.challenge.journeymiles.security.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController(private val authenticationService: AuthenticationService) {

    @PostMapping
    fun authenticate(@RequestBody authRequest: JWTAuthenticationRequest) =
        authenticationService.authenticate(authRequest)

    @PostMapping("/refresh")
    fun refreshToken(@RequestBody refreshTokenRequest: RefreshTokenRequest) =
        authenticationService.refreshToken(refreshTokenRequest)
}