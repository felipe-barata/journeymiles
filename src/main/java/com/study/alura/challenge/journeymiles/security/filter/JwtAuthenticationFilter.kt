package com.study.alura.challenge.journeymiles.security.filter

import com.study.alura.challenge.journeymiles.security.service.TokenService
import com.study.alura.challenge.journeymiles.user.service.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val userService: UserService,
    private val tokenService: TokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader(AUTH_HEADER)
        if (authHeader == null) {
            filterChain.doFilter(request, response)
            return
        }

        if (containsBearerToken(authHeader)) {
            val jwtToken = extractBearerToken(authHeader)
            val email = tokenService.extractEmail(jwtToken)

            if (email != null && SecurityContextHolder.getContext().authentication == null) {
                val user = userService.loadUserByUsername(email)

                if (tokenService.isValid(jwtToken, user)) {
                    updateContext(user, request)
                }
            }
        } else {
            verifyServiceAuthentication(authHeader)?.run {
                updateContext(this, request)
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun containsBearerToken(header: String?) = header != null &&
            header.startsWith(BEARER)

    private fun extractBearerToken(header: String) = header.substringAfter(BEARER.plus(" "))

    private fun updateContext(userDetails: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken
    }

    private fun verifyServiceAuthentication(token: String) = tokenService.verifyServiceAuth(token)

    companion object {
        private const val AUTH_HEADER = "Authorization"
        private const val BEARER = "Bearer"
    }

}
