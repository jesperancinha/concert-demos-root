package org.jesperancinha.concerts.data

import java.util.Objects

data class ConcertDto(
    val id: Long? = null,
    val name: String,
    val location: String,
    val date: String,
    val listingDtos: MutableList<ListingDto?>? = mutableListOf(),
) {

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

    override fun hashCode(): Int = Objects.hash(name, location, date, listingDtos)
}