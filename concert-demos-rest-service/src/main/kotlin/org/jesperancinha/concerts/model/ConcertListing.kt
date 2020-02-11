package org.jesperancinha.concerts.model

import org.springframework.data.annotation.Id

data class ConcertListing(
        @Id val id: Long?,
        val concertId: Long,
        val listingId: Long
) {
    constructor(concertId: Long, listingId: Long) : this(null, concertId, listingId)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConcertListing

        if (id != other.id) return false
        if (concertId != other.concertId) return false
        if (listingId != other.listingId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + concertId.hashCode()
        result = 31 * result + listingId.hashCode()
        return result
    }
}