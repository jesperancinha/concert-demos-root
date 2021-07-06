package org.jesperancinha.concerts.webflux.repos

import org.jesperancinha.concerts.webflux.model.ListingMusic
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface ListingMusicRepository : ReactiveCrudRepository<ListingMusic, Long> {

    @Query("SELECT * FROM Listing_Music WHERE listing_id = :listingId")
    fun findByListingId(listingId: Long): Mono<ListingMusic>
}
