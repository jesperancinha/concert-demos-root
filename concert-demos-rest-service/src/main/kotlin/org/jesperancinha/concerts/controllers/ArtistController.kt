package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.model.Artist
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux

interface ArtistController {

    @GetMapping
    fun getAllArtists(): Flux<Artist?>?;

    @PostMapping("/artist")
    fun createArtist(@RequestBody artist: Artist);

}
