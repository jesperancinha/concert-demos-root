package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.converters.ArtistConverter
import org.jesperancinha.concerts.converters.ArtistConverter.toArtist
import org.jesperancinha.concerts.converters.ArtistConverter.toArtistDto
import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.repos.ArtistRepository
import org.springframework.stereotype.Service

@Service
class ArtistServiceImpl(private val artistRepository: ArtistRepository) : ArtistService {

    override fun getAllArtists(): List<ArtistDto>? {
        return artistRepository.findAll().map { toArtistDto(it) }
    }

    override fun createArtist(artist: ArtistDto): ArtistDto {
        return ArtistConverter.toArtistDto(artistRepository.save(toArtist(artist)))
    }
}