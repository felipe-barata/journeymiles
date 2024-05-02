package com.study.alura.challenge.journeymiles.security.service

import com.study.alura.challenge.journeymiles.user.dto.response.UserDTO

interface AuthenticationFacade {
    fun getAuthenticatedUser(): UserDTO?
}