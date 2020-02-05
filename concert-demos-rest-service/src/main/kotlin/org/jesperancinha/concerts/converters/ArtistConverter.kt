package org.jesperancinha.concerts.converters

import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.model.Artist

object ArtistConverter {
    fun toArtistDto(artist: Artist): ArtistDto {
        return ArtistDto(
                artist.name,
                artist.gender,
                artist.careerStart,
                artist.birthDate,
                artist.birthCity,
                artist.country,
                artist.keywords
        )
    }
}