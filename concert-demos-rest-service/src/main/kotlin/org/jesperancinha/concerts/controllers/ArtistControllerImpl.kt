package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.model.Artist
import org.jesperancinha.concerts.services.ArtistService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ArtistControllerImpl(private val artistService: ArtistService) : ArtistController {

    override fun getAllArtists(): Flux<Artist?>? {
        return artistService.getAllArtists();
    }

    override fun createArtist(@RequestBody artist: Artist) {
        artistService.createArtist(artist)
    }
}
