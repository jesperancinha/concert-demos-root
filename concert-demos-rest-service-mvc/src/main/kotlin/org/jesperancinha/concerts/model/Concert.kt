package org.jesperancinha.concerts.model

import org.springframework.data.annotation.Id

data class Concert(
        @Id var id: Long? = null,
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

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}