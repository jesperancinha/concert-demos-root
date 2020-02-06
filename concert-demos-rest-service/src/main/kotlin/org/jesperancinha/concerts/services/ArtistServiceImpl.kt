package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.converters.ArtistConverter.toArtist
import org.jesperancinha.concerts.converters.ArtistConverter.toArtistDto
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.repos.ArtistRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ArtistServiceImpl(private val artistRepository: ArtistRepository) : ArtistService {

    override fun getAllArtists(): Flux<ArtistDto>? {
        return artistRepository.findAll().map { toArtistDto(it) }
    }

    override fun createArtist(artist: ArtistDto): Mono<ArtistDto> {
        return artistRepository.save(toArtist(artist)).map { toArtistDto(it) }
    }
}