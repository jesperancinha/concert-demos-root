package org.jesperancinha.concerts.mvc.services

import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.converters.ArtistConverter.toArtist
import org.jesperancinha.concerts.mvc.model.toArtistDto
import org.jesperancinha.concerts.mvc.repos.ArtistRepository
import org.springframework.stereotype.Service

@Service
class ArtistServiceImpl(private val artistRepository: ArtistRepository) : ArtistService {

    override fun getAllArtists(): List<ArtistDto>? {
        return artistRepository.findAll().map { it.toArtistDto() }
    }

    override fun createArtist(artist: ArtistDto): ArtistDto {
        return artistRepository.save(toArtist(artist)).toArtistDto()
    }
}