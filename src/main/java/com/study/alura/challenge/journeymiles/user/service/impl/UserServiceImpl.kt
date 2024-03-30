package com.study.alura.challenge.journeymiles.user.service.impl

import com.study.alura.challenge.journeymiles.model.exceptions.UserNotFoundException
import com.study.alura.challenge.journeymiles.user.dto.request.CreateUserRequestDTO
import com.study.alura.challenge.journeymiles.user.dto.response.UserResponseDTO
import com.study.alura.challenge.journeymiles.user.mappers.toDTO
import com.study.alura.challenge.journeymiles.user.mappers.toEntity
import com.study.alura.challenge.journeymiles.user.repository.UserRepository
import com.study.alura.challenge.journeymiles.user.service.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun createUser(createUserRequestDTO: CreateUserRequestDTO): UserResponseDTO {
        return userRepository.save(createUserRequestDTO.toEntity()).toDTO()
    }

    override fun findUser(userId: Long) =
        userRepository.findByIdOrNull(userId)?.toDTO() ?: throw UserNotFoundException(userId)
}