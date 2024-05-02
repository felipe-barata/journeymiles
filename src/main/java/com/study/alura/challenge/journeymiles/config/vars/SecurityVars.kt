package com.study.alura.challenge.journeymiles.config.vars

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
data class SecurityVars(
    @Value("\${config.security.shared-secret}") val sharedSecret: String,
    @Value("\${config.security.jwt-secret}") val secret: String,
    @Value("\${config.security.token-expiration}") val tokenExpiration: Long,
    @Value("\${config.security.refresh-token-expiration}") val refreshTokenExpiration: Long,
)
