package org.jesperancinha.concerts.model

data class Music(
        val id: Long,
        val name: String,
        val lyrics: String,
        val artist: Artist
) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}