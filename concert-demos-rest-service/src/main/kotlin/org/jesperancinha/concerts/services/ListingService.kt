package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.Listing
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ListingService {
    fun getAllListings(): Flux<Listing?>?

    fun createListing(listing: Listing): Mono<Listing>
}