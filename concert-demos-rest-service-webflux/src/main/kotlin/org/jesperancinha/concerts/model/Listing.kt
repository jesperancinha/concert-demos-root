package org.jesperancinha.concerts.model

import org.springframework.data.annotation.Id

data class Listing(
        @Id var id: Long? = null,
        val artistId: Long,
        val referenceMusicId: Long
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

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + artistId.hashCode()
        result = 31 * result + referenceMusicId.hashCode()
        return result.toInt()
    }

}