package com.study.alura.challenge.journeymiles.config.infra

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.time.format.DateTimeFormatter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
class JacksonConfig {

    @Bean(value = ["mapper", "objectMapper"])
    @Primary
    fun mapper(): ObjectMapper {
        return Jackson2ObjectMapperBuilder.json()
            .modules(kotlinModule(), JavaTimeModule())
            .featuresToDisable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
            )
            .featuresToEnable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .simpleDateFormat("yyyy-MM-dd")
            .serializers(
                LocalDateSerializer(DateTimeFormatter.ISO_DATE),
                LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME)
            )
            .deserializers(
                LocalDateDeserializer(DateTimeFormatter.ISO_DATE),
                LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME)
            )
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .build()
    }

    @Bean(value = ["jacksonMessageConverter"])
    @Primary

    fun jacksonMessageConverter() = MappingJackson2HttpMessageConverter(
        mapper()
    )

    private fun kotlinModule(): KotlinModule {
        return KotlinModule.Builder()
            .withReflectionCacheSize(CACHE_SIZE)
            .configure(
                KotlinFeature.NullToEmptyCollection, false
            )
            .configure(
                KotlinFeature.NullToEmptyMap, false
            )
            .configure(
                KotlinFeature.NullIsSameAsDefault, false
            )
            .configure(
                KotlinFeature.SingletonSupport, false
            )
            .configure(
                KotlinFeature.StrictNullChecks, false
            )
            .build()
    }

    companion object {
        private const val CACHE_SIZE = 512
    }
}