package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.model.Artist
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ArtistController {

    @GetMapping
    fun getAllArtists(): Flux<Artist?>?;

    @PostMapping
    fun createArtist(@RequestBody artist: Artist): Mono<Artist>;

}
