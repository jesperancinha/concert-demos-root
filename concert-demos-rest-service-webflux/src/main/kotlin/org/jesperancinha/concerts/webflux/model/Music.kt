package org.jesperancinha.concerts.webflux.model

import org.springframework.data.annotation.Id

data class Music(
        @Id var id: Long? = null,
        val name: String,
        val lyrics: String
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
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + lyrics.hashCode()
        return result
    }

}