package com.study.alura.challenge.journeymiles.destinations.dto.response

import java.io.Serializable
import java.math.BigDecimal

data class DestinationsResponseDTO(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val updatedAt: String? = null,
    val createdAt: String,
    val meta: String,
    val description: String? = null,
    val pictures: Set<String> = emptySet()
) : Serializable
