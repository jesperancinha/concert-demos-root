package org.jesperancinha.concerts.data

import java.util.Objects

data class ListingDto(
    val id: Long? = null,
    val artistDto: ArtistDto,
    val referenceMusicDto: MusicDto,
    val musicDtos: MutableList<MusicDto>,
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

    override fun hashCode(): Int = Objects.hash(id, artistDto, referenceMusicDto, musicDtos)

}