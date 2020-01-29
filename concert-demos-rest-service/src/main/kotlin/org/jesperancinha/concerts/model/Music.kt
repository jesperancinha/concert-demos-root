package org.jesperancinha.concerts.model

data class Music(
        val id: Long,
        val name: String,
        val lyrics: String,
        val artist: Artist
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Music

        if (id != other.id) return false
        if (name != other.name) return false
        if (lyrics != other.lyrics) return false
        if (artist != other.artist) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + lyrics.hashCode()
        result = 31 * result + artist.hashCode()
        return result
    }

}