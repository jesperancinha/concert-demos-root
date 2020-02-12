package org.jesperancinha.concerts.data

data class ListingDto(
        var id: Long? = null,
        val artistDto: ArtistDto? = null,
        val referenceMusicDto: MusicDto? = null,
        val musicDtos: MutableList<MusicDto>? = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListingDto

        if (id != other.id) return false
        if (artistDto != other.artistDto) return false
        if (referenceMusicDto != other.referenceMusicDto) return false
        if (musicDtos != other.musicDtos) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + artistDto.hashCode()
        result = 31 * result + referenceMusicDto.hashCode()
        result = 31 * result + musicDtos.hashCode()
        return result
    }

}