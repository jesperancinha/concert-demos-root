package org.jesperancinha.concerts.mvc.controllers

import org.springframework.core.ParameterizedTypeReference

class TestKUtils {
    companion object {
        val HEY_MAMA = """
        """.trimIndent()
    }
}

inline fun <reified T : Any> typeRef(): ParameterizedTypeReference<T> = object : ParameterizedTypeReference<T>() {}
