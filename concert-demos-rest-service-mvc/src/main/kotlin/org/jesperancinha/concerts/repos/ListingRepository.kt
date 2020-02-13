package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Listing
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface ListingRepository : CrudRepository<Listing, Long>
