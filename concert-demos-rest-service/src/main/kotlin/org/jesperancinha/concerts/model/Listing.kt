package org.jesperancinha.concerts.model

data class Listing(
        val artist: Artist,
        val music: List<Music>
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