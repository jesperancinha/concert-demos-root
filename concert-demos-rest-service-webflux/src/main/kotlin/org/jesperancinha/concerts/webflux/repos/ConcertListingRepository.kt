package org.jesperancinha.concerts.webflux.repos

import org.jesperancinha.concerts.webflux.model.ConcertListing
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ConcertListingRepository : ReactiveCrudRepository<ConcertListing, Long> {

    @Query("SELECT * FROM Concert_Listing WHERE concert_id = :concertId")
    fun findByConcertId(concertId: Long): Flux<ConcertListing>
}
