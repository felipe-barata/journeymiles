package com.study.alura.challenge.journeymiles.destinations.mappers

import com.study.alura.challenge.journeymiles.destinations.dto.request.CreateOrUpdateDestinationRequestDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.DestinationsResponseDTO
import com.study.alura.challenge.journeymiles.model.entity.DestinationEntity
import java.time.format.DateTimeFormatter

fun DestinationEntity.toResponse() = DestinationsResponseDTO(
    id = this.id!!,
    name = this.name,
    price = this.price,
    createdAt = this.createdAt.format(DateTimeFormatter.ISO_DATE_TIME),
    updatedAt = this.updatedAt?.format(DateTimeFormatter.ISO_DATE_TIME),
    description = this.description,
    meta = this.meta
)

fun CreateOrUpdateDestinationRequestDTO.toEntity() = DestinationEntity(
    name = this.name,
    price = this.price,
    description = this.description,
    meta = this.meta
)

fun List<DestinationEntity>.toResponse() = map {
    it.toResponse()
}
