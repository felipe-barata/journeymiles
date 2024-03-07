package com.study.alura.challenge.journeymiles.user.controller

import com.study.alura.challenge.journeymiles.user.dto.request.CreateUserRequestDTO
import com.study.alura.challenge.journeymiles.user.dto.response.CreateUserResponseDTO
import com.study.alura.challenge.journeymiles.user.service.UserService
import jakarta.validation.Valid
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    private val logger = LogManager.getLogger(this::class.java)

    @PostMapping
    fun create(@RequestBody @Valid createUserRequestDTO: CreateUserRequestDTO): ResponseEntity<CreateUserResponseDTO> {
        return ResponseEntity.ok(
            userService.createUser(createUserRequestDTO).also {
                logger.info("Created a new user with id: ${it.id}")
            }
        )
    }
}