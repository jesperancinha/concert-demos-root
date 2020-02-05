package org.jesperancinha.concerts.data

data class MusicDto(
        var id: Int? = null,
        val name: String,
        val lyrics: String,
        val artistDto: ArtistDto
) {

    constructor(name: String,
                lyrics: String,
                artistDto: ArtistDto) :
            this(null,
                    name,
                    lyrics,
                    artistDto
            )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MusicDto

        if (name != other.name) return false
        if (lyrics != other.lyrics) return false
        if (artistDto != other.artistDto) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + lyrics.hashCode()
        result = 31 * result + artistDto.hashCode()
        return result
    }

}