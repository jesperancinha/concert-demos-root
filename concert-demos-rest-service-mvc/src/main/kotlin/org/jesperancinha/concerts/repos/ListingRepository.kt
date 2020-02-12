package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Listing
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface ListingRepository : CrudRepository<Listing, Long>
