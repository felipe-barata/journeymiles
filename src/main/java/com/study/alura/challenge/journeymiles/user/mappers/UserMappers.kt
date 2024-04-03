package com.study.alura.challenge.journeymiles.user.mappers

import com.study.alura.challenge.journeymiles.model.entity.UserEntity
import com.study.alura.challenge.journeymiles.user.dto.request.CreateUserRequestDTO
import com.study.alura.challenge.journeymiles.user.dto.response.UserResponseDTO

fun CreateUserRequestDTO.toEntity() = UserEntity(
    name = this.name,
    email = this.email,
    password = this.password
)

fun UserEntity.toDTO() = UserResponseDTO(
    name = this.name,
    email = this.email,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    id = this.id!!
)

fun UserResponseDTO.toEntity() = UserEntity(
    id = this.id,
    name = this.name,
    email = this.email,
    password = "",
    createdAt = this.createdAt,
    updatedAt = this.updatedAt
)