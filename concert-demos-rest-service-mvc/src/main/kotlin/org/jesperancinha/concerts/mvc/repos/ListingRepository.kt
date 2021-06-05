package org.jesperancinha.concerts.mvc.repos

import org.jesperancinha.concerts.mvc.model.Listing
import org.springframework.data.repository.CrudRepository

interface ListingRepository : CrudRepository<Listing, Long>
