package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.data.ConcertDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ConcertController {

    @GetMapping
    fun getAllConcerts(): Flux<ConcertDto>?

    @PostMapping
    fun createConcert(@RequestBody concertDto: ConcertDto): Mono<ConcertDto>

}
