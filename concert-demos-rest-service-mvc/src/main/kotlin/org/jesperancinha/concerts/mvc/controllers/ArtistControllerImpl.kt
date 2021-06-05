package org.jesperancinha.concerts.mvc.controllers

import org.jesperancinha.concerts.data.ArtistDto
import org.jesperancinha.concerts.mvc.services.ArtistService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/concerts/data/artists")
class ArtistControllerImpl(private val artistService: ArtistService) :
    ArtistController {

    override fun getAllArtists(): List<ArtistDto>? {
        return artistService.getAllArtists()
    }

    override fun createArtist(@RequestBody artistDto: ArtistDto): ArtistDto {
        return artistService.createArtist(artistDto)
    }
}
