package com.study.alura.challenge.journeymiles.config.security

import com.study.alura.challenge.journeymiles.user.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SpringSecurityConfiguration {

    @Bean
    fun userDetailsService(userService: UserService): UserDetailsService = userService

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(userService: UserService) = DaoAuthenticationProvider()
        .also {
            it.setUserDetailsService(userService)
            it.setPasswordEncoder(passwordEncoder())
        }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration) = config.authenticationManager
}