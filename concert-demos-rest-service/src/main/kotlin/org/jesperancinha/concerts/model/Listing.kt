package org.jesperancinha.concerts.model

data class Listing(
        val artist: Artist,
        val music: List<Music>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Listing

        if (artist != other.artist) return false
        if (music != other.music) return false

        return true
    }

    override fun hashCode(): Int {
        var result = artist.hashCode()
        result = 31 * result + music.hashCode()
        return result
    }

}