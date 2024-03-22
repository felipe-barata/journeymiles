package com.study.alura.challenge.journeymiles.destinations.dto.request

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable
import java.math.BigDecimal

data class CreateOrUpdateDestinationRequestDTO(
    @field:NotNull(message = "The name field is mandatory")
    @field:Size(min = 4, max = 200, message = "The name field must be between 4 and 200 chars")
    val name: String,
    @field:NotNull(message = "The price field is mandatory")
    @field:DecimalMin(value = "0.01", message = "The price field must be positive value")
    @field:Digits(integer = 16, fraction = 2)
    val price: BigDecimal,
    @field:NotNull(message = "The meta field is mandatory")
    val meta: String,
    val description: String? = null
) : Serializable
