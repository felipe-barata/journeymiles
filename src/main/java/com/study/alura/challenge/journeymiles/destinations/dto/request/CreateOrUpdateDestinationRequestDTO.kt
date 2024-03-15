package com.study.alura.challenge.journeymiles.destinations.dto.request

import java.io.Serializable
import java.math.BigDecimal

data class CreateOrUpdateDestinationRequestDTO(
    val name: String,
    val price: BigDecimal,
    val meta: String,
    val description: String? = null
) : Serializable
