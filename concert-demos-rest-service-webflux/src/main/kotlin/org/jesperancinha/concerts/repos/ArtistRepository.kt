package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Artist
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ArtistRepository : ReactiveCrudRepository<Artist, Long>
