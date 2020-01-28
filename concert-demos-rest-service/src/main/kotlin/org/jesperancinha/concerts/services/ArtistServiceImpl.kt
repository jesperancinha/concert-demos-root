package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.Artist
import org.jesperancinha.concerts.repos.ArtistRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ArtistServiceImpl(private val artistRepository: ArtistRepository) : ArtistService {

    override fun getAllArtists(): Flux<Artist?>? {
        return artistRepository.findAll();
    }

    override fun createArtist(artist: Artist) {
        artistRepository.save(artist);
    }
}