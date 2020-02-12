package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.data.ArtistDto

interface ArtistService {
    fun getAllArtists(): List<ArtistDto>?

    fun createArtist(artist: ArtistDto): ArtistDto
}