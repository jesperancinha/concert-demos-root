package org.jesperancinha.concerts.data

data class MusicDto(
    val id: Long?,
    val name: String,
    val lyrics: String,
) {
    constructor(
        name: String,
        lyrics: String,
    ) :
            this(null,
                name,
                lyrics
            )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MusicDto

        if (name != other.name) return false
        if (lyrics != other.lyrics) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + lyrics.hashCode()
        return result
    }

}