package org.jesperancinha.concerts.data

data class ListingDto(
        var id: Int? = null,
        val artistDto: ArtistDto,
        val musicDtos: List<MusicDto>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListingDto

        if (artistDto != other.artistDto) return false
        if (musicDtos != other.musicDtos) return false

        return true
    }

    override fun hashCode(): Int {
        var result = artistDto.hashCode()
        result = 31 * result + musicDtos.hashCode()
        return result
    }

}