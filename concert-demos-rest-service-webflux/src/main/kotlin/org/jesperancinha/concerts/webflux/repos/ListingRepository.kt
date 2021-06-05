package org.jesperancinha.concerts.webflux.repos

import org.jesperancinha.concerts.webflux.model.Listing
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ListingRepository : ReactiveCrudRepository<Listing, Long>
