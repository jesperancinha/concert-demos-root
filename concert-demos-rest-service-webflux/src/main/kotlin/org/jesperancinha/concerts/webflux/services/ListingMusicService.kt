package org.jesperancinha.concerts.webflux.services

import org.jesperancinha.concerts.webflux.model.ListingMusic
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ListingMusicService {
    fun getAllListings(): Flux<ListingMusic?>?

    fun createListing(listingMusic: ListingMusic): Mono<ListingMusic>
}