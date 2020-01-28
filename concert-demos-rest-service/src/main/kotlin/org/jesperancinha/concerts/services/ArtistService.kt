package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.Artist
import reactor.core.publisher.Flux

interface ArtistService {

    fun getAllArtists(): Flux<Artist?>?

    fun createArtist(artist: Artist)

}