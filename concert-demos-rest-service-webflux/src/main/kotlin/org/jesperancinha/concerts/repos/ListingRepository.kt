package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Listing
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ListingRepository : ReactiveCrudRepository<Listing, Long>
