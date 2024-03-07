package com.study.alura.challenge.journeymiles.user.service.impl

import com.study.alura.challenge.journeymiles.user.dto.request.CreateUserRequestDTO
import com.study.alura.challenge.journeymiles.user.dto.response.CreateUserResponseDTO
import com.study.alura.challenge.journeymiles.user.mappers.toDTO
import com.study.alura.challenge.journeymiles.user.mappers.toEntity
import com.study.alura.challenge.journeymiles.user.repository.UserRepository
import com.study.alura.challenge.journeymiles.user.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun createUser(createUserRequestDTO: CreateUserRequestDTO): CreateUserResponseDTO {
        return userRepository.save(createUserRequestDTO.toEntity()).toDTO()
    }
}