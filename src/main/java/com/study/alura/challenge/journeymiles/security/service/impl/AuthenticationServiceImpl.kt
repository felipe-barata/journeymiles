package com.study.alura.challenge.journeymiles.security.service.impl

import com.study.alura.challenge.journeymiles.model.exceptions.ExpiredRefreshTokenException
import com.study.alura.challenge.journeymiles.security.dto.AuthenticationResponseDTO
import com.study.alura.challenge.journeymiles.security.dto.JWTAuthenticationRequest
import com.study.alura.challenge.journeymiles.security.dto.RefreshTokenRequest
import com.study.alura.challenge.journeymiles.security.dto.TokenResponseDTO
import com.study.alura.challenge.journeymiles.security.repository.RefreshTokenRepository
import com.study.alura.challenge.journeymiles.security.service.AuthenticationService
import com.study.alura.challenge.journeymiles.security.service.TokenService
import com.study.alura.challenge.journeymiles.user.service.UserService
import org.apache.logging.log4j.LogManager
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserService,
    private val tokenService: TokenService,
    private val refreshTokenRepository: RefreshTokenRepository
) : AuthenticationService {

    private val logger = LogManager.getLogger(this::class.java)

    override fun authenticate(request: JWTAuthenticationRequest): AuthenticationResponseDTO {
        logger.info("Authentication with user ${request.email}")
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )

        val user = userDetailsService.loadUserByUsername(request.email)

        val token = createAccessToken(user)
        val refreshToken = createRefreshToken(user)

        refreshTokenRepository.save(refreshToken, user)

        logger.info("Authenticated with user ${request.email}")
        return AuthenticationResponseDTO(token, refreshToken)
    }

    override fun refreshToken(refreshTokenRequest: RefreshTokenRequest): TokenResponseDTO {
        val token = refreshTokenRequest.token
        val email = tokenService.extractEmail(token)
        val newToken = email?.let {
            val currentUserDetails = userDetailsService.loadUserByUsername(it)
            val refreshTokenUserDetails = refreshTokenRepository.findUserDetailsByToken(token)

            if (!tokenService.isExpired(token) && refreshTokenUserDetails?.username == currentUserDetails.username) {
                createAccessToken(currentUserDetails)
            } else null
        }

        return newToken?.let { TokenResponseDTO(newToken) } ?: throw ExpiredRefreshTokenException()
    }

    private fun createAccessToken(userDetails: UserDetails) = tokenService.generate(userDetails)

    private fun createRefreshToken(userDetails: UserDetails) = tokenService.generateRefreshToken(userDetails)
}