package org.jesperancinha.concerts.webflux.services

import org.jesperancinha.concerts.webflux.model.ListingMusic
import org.jesperancinha.concerts.webflux.repos.ListingMusicRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ListingMusicServiceImpl(private val listingMusicRepository: ListingMusicRepository) : ListingMusicService {
    override fun getAllListings(): Flux<ListingMusic?>? {
        return listingMusicRepository.findAll()
    }

    override fun createListing(listingMusic: ListingMusic): Mono<ListingMusic> {
        return listingMusicRepository.save(listingMusic)
    }
}