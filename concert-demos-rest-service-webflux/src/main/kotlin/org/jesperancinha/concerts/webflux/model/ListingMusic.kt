package org.jesperancinha.concerts.webflux.model

import org.springframework.data.annotation.Id
import java.util.Objects

data class ListingMusic(
        @Id val id: Long?,
        val listingId: Long?,
        val musicId: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListingMusic

        if (id != other.id) return false
        if (listingId != other.listingId) return false
        if (musicId != other.musicId) return false

        return true
    }

    override fun hashCode(): Int = Objects.hash(id, listingId, musicId)

}