package com.study.alura.challenge.journeymiles.user.dto.response

import java.io.Serializable
import java.time.OffsetDateTime

data class UserDTO(
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    val updatedAt: OffsetDateTime? = null,
): Serializable
