package com.study.alura.challenge.journeymiles.security.service.impl

import com.study.alura.challenge.journeymiles.config.vars.SecurityVars
import com.study.alura.challenge.journeymiles.security.dto.ServiceUserDetail
import com.study.alura.challenge.journeymiles.security.service.TokenService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.*
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class TokenServiceImpl(private val securityVars: SecurityVars) : TokenService {

    override fun generate(
        userDetails: UserDetails,
        additionalClaims: Map<String, Any>
    ) = getToken(userDetails.username, additionalClaims, getTokenExpiration())

    override

    fun generateRefreshToken(userDetails: UserDetails, additionalClaims: Map<String, Any>) =
        getToken(userDetails.username, additionalClaims, getRefreshTokenExpiration())

    override fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)
        return userDetails.username == email && !isExpired(token)
    }

    override fun extractEmail(token: String): String? = getAllClaims(token).subject

    override fun isExpired(token: String) = getAllClaims(token)
        .expiration.before(Date(System.currentTimeMillis()))

    override fun verifyServiceAuth(token: String) = takeIf { token.compareTo(securityVars.sharedSecret) == 0 }?.let {
        ServiceUserDetail(securityVars.sharedSecret)
    }

    private fun getAllClaims(token: String) = Jwts.parser()
        .verifyWith(secretKey).build().parseSignedClaims(token).payload

    private fun getTokenExpiration() = Date(System.currentTimeMillis().plus(securityVars.tokenExpiration))

    private fun getRefreshTokenExpiration() = Date(System.currentTimeMillis().plus(securityVars.refreshTokenExpiration))

    private fun getToken(username: String, additionalClaims: Map<String, Any>, tokenExpiration: Date) = Jwts.builder()
        .claims()
        .subject(username)
        .issuedAt(Date(System.currentTimeMillis()))
        .expiration(tokenExpiration)
        .add(additionalClaims)
        .and()
        .signWith(secretKey)
        .compact()

    private val secretKey = Keys.hmacShaKeyFor(securityVars.secret.toByteArray())
}