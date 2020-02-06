package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.Concert
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ConcertService {
    fun getAllConcerts(): Flux<Concert?>?

    fun createConcert(concert: Concert): Mono<Concert>
}