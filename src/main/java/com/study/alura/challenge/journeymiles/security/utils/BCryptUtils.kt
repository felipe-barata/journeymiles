package com.study.alura.challenge.journeymiles.security.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BCryptUtils {

    fun generate(value: String) = BCryptPasswordEncoder().encode(value)

    fun verifyString(value: String, crypto: String) = BCryptPasswordEncoder().matches(value, crypto)

}
