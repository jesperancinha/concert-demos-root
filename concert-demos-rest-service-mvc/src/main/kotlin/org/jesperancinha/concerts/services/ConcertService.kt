package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.data.ConcertDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ConcertService {
    fun getAllConcerts(): List<ConcertDto>

    fun createConcert(concertDto: ConcertDto): ConcertDto
}