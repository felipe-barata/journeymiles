package com.study.alura.challenge.journeymiles.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity(name = "destinations")
data class DestinationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @Column(name = "price", precision = 16, scale = 2)
    val price: BigDecimal,
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    val updatedAt: OffsetDateTime? = null
)
