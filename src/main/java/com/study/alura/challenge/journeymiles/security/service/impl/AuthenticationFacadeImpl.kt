package com.study.alura.challenge.journeymiles.security.service.impl

import com.study.alura.challenge.journeymiles.security.service.AuthenticationFacade
import com.study.alura.challenge.journeymiles.user.dto.response.UserDTO
import com.study.alura.challenge.journeymiles.user.service.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationFacadeImpl(private val userService: UserService) : AuthenticationFacade {
    override fun getAuthenticatedUser(): UserDTO? {
        val auth = SecurityContextHolder.getContext().authentication
        return takeIf { auth.isAuthenticated }?.run {
            userService.findUserByUsername(auth.name)
        }
    }
}