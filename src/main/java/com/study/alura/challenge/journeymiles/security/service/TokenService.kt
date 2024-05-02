package com.study.alura.challenge.journeymiles.security.service

import org.springframework.security.core.userdetails.UserDetails

interface TokenService {

    fun generate(
        userDetails: UserDetails,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String

    fun generateRefreshToken(
        userDetails: UserDetails,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String

    fun isValid(token: String, userDetails: UserDetails): Boolean

    fun extractEmail(token: String): String?

    fun isExpired(token: String): Boolean

    fun verifyServiceAuth(token: String): UserDetails?
}