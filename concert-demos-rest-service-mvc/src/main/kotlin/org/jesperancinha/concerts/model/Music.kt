package org.jesperancinha.concerts.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Music(
        @Id @GeneratedValue
        var id: Long? = null,
        val name: String? = null,
        val lyrics: String? = null
) {

    @ManyToOne
    lateinit var listing: Listing

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Music

        if (id != other.id) return false
        if (name != other.name) return false
        if (lyrics != other.lyrics) return false
        if (listing != other.listing) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (lyrics?.hashCode() ?: 0)
        result = 31 * result + listing.hashCode()
        return result
    }

}