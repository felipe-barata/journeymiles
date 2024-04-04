package com.study.alura.challenge.journeymiles.destinations.dto.response

import java.io.Serializable
import java.math.BigDecimal

data class SearchDestinationsResponseDTO(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val pictures: Set<String> = emptySet()
) : Serializable
