package org.jesperancinha.concerts.data

import java.util.Objects

data class MusicDto(
    val id: Long? = null,
    val name: String,
    val lyrics: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MusicDto

        if (name != other.name) return false
        if (lyrics != other.lyrics) return false

        return true
    }

    override fun hashCode(): Int = Objects.hash(name, lyrics)

}