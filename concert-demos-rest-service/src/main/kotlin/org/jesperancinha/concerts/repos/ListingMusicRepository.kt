package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.ListingMusic
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ListingMusicRepository : ReactiveCrudRepository<ListingMusic, Long>
