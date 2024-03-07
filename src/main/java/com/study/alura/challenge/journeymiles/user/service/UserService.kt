package com.study.alura.challenge.journeymiles.user.service

import com.study.alura.challenge.journeymiles.user.dto.request.CreateUserRequestDTO
import com.study.alura.challenge.journeymiles.user.dto.response.CreateUserResponseDTO

interface UserService {
    fun createUser(createUserRequestDTO: CreateUserRequestDTO): CreateUserResponseDTO
}