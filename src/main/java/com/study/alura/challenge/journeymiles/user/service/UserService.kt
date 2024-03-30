package com.study.alura.challenge.journeymiles.user.service

import com.study.alura.challenge.journeymiles.user.dto.request.CreateUserRequestDTO
import com.study.alura.challenge.journeymiles.user.dto.response.UserResponseDTO

interface UserService {
    fun createUser(createUserRequestDTO: CreateUserRequestDTO): UserResponseDTO

    fun findUser(userId: Long): UserResponseDTO
}