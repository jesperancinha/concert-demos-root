package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Listing
import org.springframework.data.repository.CrudRepository

interface ListingRepository : CrudRepository<Listing, Long>
