package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.ListingMusic
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface ListingMusicRepository : ReactiveCrudRepository<ListingMusic, Long> {

    @Query("SELECT * FROM Listing_Music WHERE listing_id = :listingId")
    fun findByListingId(listingId: Long): Mono<ListingMusic>
}
