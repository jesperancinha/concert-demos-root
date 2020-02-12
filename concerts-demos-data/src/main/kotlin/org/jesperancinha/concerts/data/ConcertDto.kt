package org.jesperancinha.concerts.data

import java.time.LocalDateTime

data class ConcertDto(
        var id: Long? = null,
        val name: String,
        val location: String,
        val date: String,
        val listingDtos: MutableList<ListingDto> = mutableListOf()
) {
    constructor(name: String, location: String, localDateTime: String, listingDtos: MutableList<ListingDto>) :
            this(null, name, location, localDateTime, listingDtos)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConcertDto

        if (name != other.name) return false
        if (location != other.location) return false
        if (date != other.date) return false
        if (listingDtos != other.listingDtos) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + listingDtos.hashCode()
        return result
    }
}