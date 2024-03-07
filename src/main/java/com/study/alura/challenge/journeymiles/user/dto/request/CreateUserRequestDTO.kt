package com.study.alura.challenge.journeymiles.user.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable

data class CreateUserRequestDTO(
    @field:NotNull(message = "The name field is mandatory")
    @field:Size(min = 3, max = 100, message = "The name field must bet between 3 and 100 chars")
    val name: String,
    @field:NotNull(message = "The email field is mandatory")
    @field:Email
    val email: String,
    @field:NotNull(message = "The password field is mandatory")
    @field:Size(min = 6, max = 12, message = "The password field must bet between 6 and 12 chars")
    val password: String
) : Serializable
