package org.jesperancinha.concerts.webflux.services

import org.jesperancinha.concerts.data.ConcertDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ConcertService {
    fun getAllConcerts(): Flux<ConcertDto>

    fun createConcert(concertDto: ConcertDto): Mono<ConcertDto>
}