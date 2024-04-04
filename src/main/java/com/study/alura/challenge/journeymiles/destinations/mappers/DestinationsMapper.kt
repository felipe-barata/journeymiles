package com.study.alura.challenge.journeymiles.destinations.mappers

import com.study.alura.challenge.journeymiles.destinations.dto.request.CreateOrUpdateDestinationRequestDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.DestinationsResponseDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.SearchDestinationsResponseDTO
import com.study.alura.challenge.journeymiles.model.entity.DestinationsEntity
import java.time.format.DateTimeFormatter

fun DestinationsEntity.toResponse(pictures: Set<String> = emptySet()) = DestinationsResponseDTO(
    id = this.id!!,
    name = this.name,
    price = this.price,
    createdAt = this.createdAt.format(DateTimeFormatter.ISO_DATE_TIME),
    updatedAt = this.updatedAt?.format(DateTimeFormatter.ISO_DATE_TIME),
    description = this.description,
    meta = this.meta,
    pictures = pictures
)

fun CreateOrUpdateDestinationRequestDTO.toEntity() = DestinationsEntity(
    name = this.name,
    price = this.price,
    description = this.description,
    meta = this.meta
)

fun DestinationsEntity.toSearchResponseDTO(pictures: Set<String> = emptySet()) = SearchDestinationsResponseDTO(
    name = this.name,
    id = this.id!!,
    price = this.price,
    pictures = pictures
)

fun List<DestinationsEntity>.toSearchResponseDTO() = map {
    it.toSearchResponseDTO()
}
