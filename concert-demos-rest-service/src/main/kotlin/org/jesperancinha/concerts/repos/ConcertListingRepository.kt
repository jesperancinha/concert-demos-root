package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.ConcertListing
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ConcertListingRepository : ReactiveCrudRepository<ConcertListing, Long>
