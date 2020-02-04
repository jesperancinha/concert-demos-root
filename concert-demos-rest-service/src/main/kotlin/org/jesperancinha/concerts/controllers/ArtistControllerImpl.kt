package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.model.Artist
import org.jesperancinha.concerts.services.ArtistService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/concerts/data/artists")
class ArtistControllerImpl(private val artistService: ArtistService) : ArtistController {

    override fun getAllArtists(): Flux<Artist?>? {
        return artistService.getAllArtists();
    }

    override fun createArtist(@RequestBody artist: Artist): Mono<Artist> {
       return artistService.createArtist(artist)
    }
}
