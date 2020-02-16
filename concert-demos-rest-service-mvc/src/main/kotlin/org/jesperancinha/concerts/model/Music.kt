package org.jesperancinha.concerts.model

import javax.persistence.*

@Entity
data class Music(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        val name: String? = null,
        val lyrics: String? = null,
        @ManyToMany(mappedBy = "musics")
        var listings: MutableSet<Listing>? = HashSet()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Music

        if (id != other.id) return false
        if (name != other.name) return false
        if (lyrics != other.lyrics) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (lyrics?.hashCode() ?: 0)
        return result
    }

}