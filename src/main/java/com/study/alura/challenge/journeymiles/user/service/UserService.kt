package com.study.alura.challenge.journeymiles.user.service

import com.study.alura.challenge.journeymiles.user.dto.request.CreateUserRequestDTO
import com.study.alura.challenge.journeymiles.user.dto.response.UserDTO
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.multipart.MultipartFile

interface UserService: UserDetailsService {
    fun createUser(createUserRequestDTO: CreateUserRequestDTO, picture: MultipartFile?): UserDTO

    fun findUser(userId: Long): UserDTO

    fun verifyIfEmailExists(email: String): Boolean

    fun verifyIfUserExists(userId: Long): Boolean

    fun findUserByUsername(email: String): UserDTO
}