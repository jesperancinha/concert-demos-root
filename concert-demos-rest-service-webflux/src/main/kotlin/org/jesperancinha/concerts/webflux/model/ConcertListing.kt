package org.jesperancinha.concerts.webflux.model

import org.springframework.data.annotation.Id
import java.util.Objects

data class ConcertListing(
        @Id val id: Long?,
        val concertId: Long?,
        val listingId: Long?
) {
    constructor(concertId: Long?, listingId: Long?) : this(null, concertId, listingId)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConcertListing

        if (id != other.id) return false
        if (concertId != other.concertId) return false
        if (listingId != other.listingId) return false

        return true
    }

    override fun hashCode(): Int = Objects.hash(id, concertId, listingId)
}