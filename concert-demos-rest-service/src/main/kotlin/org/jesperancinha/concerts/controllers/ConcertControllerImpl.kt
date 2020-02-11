package org.jesperancinha.concerts.controllers

import org.jesperancinha.concerts.data.ConcertDto
import org.jesperancinha.concerts.services.ConcertService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/concerts/data/concerts")
class ConcertControllerImpl(private val concertService: ConcertService) : ConcertController {
    override fun getAllConcerts(): Flux<ConcertDto> {
        return concertService.getAllConcerts()
    }

    override fun createConcert(concertDto: ConcertDto): Mono<ConcertDto> {
        return concertService.createConcert(concertDto)
    }
}