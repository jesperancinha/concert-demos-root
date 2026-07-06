package org.jesperancinha.concerts.webflux.model

import org.springframework.data.annotation.Id
import java.util.Objects

data class Listing(
    @Id val id: Long?,
    val artistId: Long?,
    val referenceMusicId: Long?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Listing

        if (id != other.id) return false
        if (artistId != other.artistId) return false
        if (referenceMusicId != other.referenceMusicId) return false

        return true
    }

    override fun hashCode(): Int = Objects.hash(id, artistId, referenceMusicId)

}