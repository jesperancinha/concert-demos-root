package org.jesperancinha.concerts.mvc.converters

import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.model.Artist

object ArtistConverter {

    fun toArtist(artistDto: ArtistDto): Artist {
        return Artist(
            artistDto.id,
            artistDto.name,
            artistDto.gender,
            artistDto.careerStart,
            artistDto.birthDate,
            artistDto.birthCity,
            artistDto.country,
            artistDto.keywords
        )
    }
}