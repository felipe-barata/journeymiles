package com.study.alura.challenge.journeymiles.destinations.controller

import com.study.alura.challenge.journeymiles.destinations.dto.request.CreateOrUpdateDestinationRequestDTO
import com.study.alura.challenge.journeymiles.destinations.dto.response.DestinationsResponseDTO
import com.study.alura.challenge.journeymiles.destinations.service.DestinationsService
import jakarta.validation.Valid
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/destinations")
class DestinationsController(private val destinationsService: DestinationsService) {

    private val logger = LogManager.getLogger(this::class.java)

    @PostMapping
    fun create(
        @Valid @ModelAttribute createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO,
        bindingResult: BindingResult
    ): ResponseEntity<DestinationsResponseDTO> {
        return ResponseEntity.ok(
            destinationsService.createDestination(createOrUpdateDestinationRequestDTO).also { destinationsResponse ->
                logger.info("Created a new testimonial with id ${destinationsResponse.id} and name: ${destinationsResponse.name}")
            })
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @ModelAttribute createOrUpdateDestinationRequestDTO: CreateOrUpdateDestinationRequestDTO,
        bindingResult: BindingResult
    ): ResponseEntity<DestinationsResponseDTO> {
        return ResponseEntity.ok(
            destinationsService.updateDestination(createOrUpdateDestinationRequestDTO, id)
                .also { destinationsResponse ->
                    logger.info("Updated a testimonial with id ${destinationsResponse.id} and name: ${destinationsResponse.name}")
                })
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> = destinationsService.deleteDestination(id).let {
        ResponseEntity.accepted().build()
    }
}