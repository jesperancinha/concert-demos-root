package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.ConcertListing
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface ConcertListingRepository : ReactiveCrudRepository<ConcertListing, Long> {

    @Query("SELECT * FROM Concert_Listing WHERE concert_id = :concertId")
    fun findByConcertId(concertId: Long): Flux<ConcertListing>
}
