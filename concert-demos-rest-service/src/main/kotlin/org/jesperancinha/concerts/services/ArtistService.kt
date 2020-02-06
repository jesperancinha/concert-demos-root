package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.data.ArtistDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ArtistService {
    fun getAllArtists(): Flux<ArtistDto>?

    fun createArtist(artist: ArtistDto): Mono<ArtistDto>
}