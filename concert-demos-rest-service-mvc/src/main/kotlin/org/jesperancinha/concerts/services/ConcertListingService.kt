package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.ConcertListing
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ConcertListingService {
    fun getAllConcerts(): Flux<ConcertListing?>?

    fun createConcert(concertListing: ConcertListing): Mono<ConcertListing>
}