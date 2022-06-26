package org.jesperancinha.concerts.mvc.services

import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.daos.ArtistRepository
import org.jesperancinha.concerts.mvc.daos.toArtist
import org.jesperancinha.concerts.mvc.daos.toArtistDto
import org.springframework.stereotype.Service

@Service
class ArtistServiceImpl(private val artistRepository: ArtistRepository) : ArtistService {

    override fun getAllArtists(): List<ArtistDto>? {
        return artistRepository.findAll().map { it.toArtistDto() }
    }

    override fun createArtist(artist: ArtistDto): ArtistDto {
        return artistRepository.save(artist.toArtist()).toArtistDto()
    }
}