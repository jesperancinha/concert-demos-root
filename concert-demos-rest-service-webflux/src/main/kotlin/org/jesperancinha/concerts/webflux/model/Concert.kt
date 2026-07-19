package org.jesperancinha.concerts.webflux.model

import org.springframework.data.annotation.Id
import java.util.Objects

data class Concert(
        @Id val id: Long?,
        val name: String,
        val location: String,
        val date: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Concert

        if (name != other.name) return false
        if (location != other.location) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int = Objects.hash(name, location, date)
}