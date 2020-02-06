package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.services.ConcertService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class ConcertControllerImpl(private val concertService: ConcertService) : ConcertController {
    override fun getAllConcerts(): Flux<ConcertDto>? {
        return concertService.getAllConcerts()
    }

    override fun createConcert(concertDto: ConcertDto): Mono<ConcertDto> {
        return concertService.createConcert(concertDto)
    }
}