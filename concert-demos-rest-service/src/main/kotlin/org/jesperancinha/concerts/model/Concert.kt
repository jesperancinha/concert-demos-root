package org.jesperancinha.concerts.model

import java.time.LocalDateTime

data class Concert(
        val name: String,
        val location: String,
        val date: LocalDateTime,
        val listings: List<Listing>
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Concert

        if (name != other.name) return false
        if (location != other.location) return false
        if (date != other.date) return false
        if (listings != other.listings) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + listings.hashCode()
        return result
    }
}