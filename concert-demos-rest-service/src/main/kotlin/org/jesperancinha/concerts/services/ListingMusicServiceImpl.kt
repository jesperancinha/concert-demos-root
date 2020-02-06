package org.jesperancinha.concerts.services

import org.jesperancinha.concerts.model.ListingMusic
import org.jesperancinha.concerts.repos.ListingMusicRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class ListingMusicServiceImpl(private val listingMusicRepository: ListingMusicRepository) : ListingMusicService {
    override fun getAllListings(): Flux<ListingMusic?>? {
        return listingMusicRepository.findAll()
    }

    override fun createListing(listingMusic: ListingMusic): Mono<ListingMusic> {
        return listingMusicRepository.save(listingMusic)
    }
}