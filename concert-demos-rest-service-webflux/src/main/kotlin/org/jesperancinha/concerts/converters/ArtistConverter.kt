package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.model.Artist

object ArtistConverter {
    fun toArtistDto(artist: Artist): ArtistDto {
        return ArtistDto(
                artist.id,
                artist.name,
                artist.gender,
                artist.careerStart,
                artist.birthDate,
                artist.birthCity,
                artist.country,
                artist.keywords
        )
    }

    fun toArtist(artistDto: ArtistDto): Artist {
        return Artist(
                artistDto.id,
                artistDto.name!!,
                artistDto.gender!!,
                artistDto.careerStart!!,
                artistDto.birthDate!!,
                artistDto.birthCity!!,
                artistDto.country!!,
                artistDto.keywords!!
        )
    }
}