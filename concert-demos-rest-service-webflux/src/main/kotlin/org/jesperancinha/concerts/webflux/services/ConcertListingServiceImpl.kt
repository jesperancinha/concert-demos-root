package org.jesperancinha.concerts.webflux.services

import org.jesperancinha.concerts.webflux.model.ConcertListing
import org.jesperancinha.concerts.webflux.repos.ConcertListingRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ConcertListingServiceImpl(private val concertListingRepository: ConcertListingRepository) :
    ConcertListingService {
    override fun getAllConcerts(): Flux<ConcertListing?>? {
        return concertListingRepository.findAll()
    }

    override fun createConcert(concertListing: ConcertListing): Mono<ConcertListing> {
        return concertListingRepository.save(concertListing)
    }
}