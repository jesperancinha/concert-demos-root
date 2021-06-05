package org.jesperancinha.concerts.webflux.services

import org.jesperancinha.concerts.webflux.model.ConcertListing
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ConcertListingService {
    fun getAllConcerts(): Flux<ConcertListing?>?

    fun createConcert(concertListing: ConcertListing): Mono<ConcertListing>
}