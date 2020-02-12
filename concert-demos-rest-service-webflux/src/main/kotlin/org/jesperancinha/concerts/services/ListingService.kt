package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.data.ListingDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ListingService {
    fun getAllListings(): Flux<ListingDto>?

    fun createListing(listingDto: ListingDto): Mono<ListingDto>

    fun getListingById(id: Long): Mono<ListingDto>
}