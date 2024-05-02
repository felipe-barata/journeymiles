package com.study.alura.challenge.journeymiles.user.controller

import com.study.alura.challenge.journeymiles.user.dto.request.CreateUserRequestDTO
import com.study.alura.challenge.journeymiles.user.dto.response.UserDTO
import com.study.alura.challenge.journeymiles.user.service.UserService
import jakarta.validation.Valid
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    private val logger = LogManager.getLogger(this::class.java)

    @PostMapping
    fun create(
        @RequestParam(value = "picture", required = false) picture: MultipartFile?,
        @RequestBody @Valid createUserRequestDTO: CreateUserRequestDTO
    ): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(
            userService.createUser(createUserRequestDTO, picture).also {
                logger.info("Created a new user with id: ${it.id}")
            }
        )
    }
}