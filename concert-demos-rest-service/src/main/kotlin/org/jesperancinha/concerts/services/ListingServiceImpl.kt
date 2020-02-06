package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.Listing
import org.jesperancinha.concerts.repos.ListingRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ListingServiceImpl(private val listingRepository: ListingRepository) : ListingService {
    override fun getAllListings(): Flux<Listing?>? {
        return listingRepository.findAll()
    }

    override fun createListing(listing: Listing): Mono<Listing> {
        return listingRepository.save(listing)
    }
}