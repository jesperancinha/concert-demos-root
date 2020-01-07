package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.repos.ArtistRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ArtistController(private var artistRepository: ArtistRepository?) {

    @GetMapping()
    fun getAllAccounts(): Flux<String?>? {
        return Flux.just("wow");
    }
}
