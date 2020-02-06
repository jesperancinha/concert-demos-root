package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.Artist
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ArtistService {
    fun getAllArtists(): Flux<Artist?>?

    fun createArtist(artist: Artist): Mono<Artist>
}