package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.ListingMusic
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ListingMusicService {
    fun getAllListings(): Flux<ListingMusic?>?

    fun createListing(listingMusic: ListingMusic): Mono<ListingMusic>
}