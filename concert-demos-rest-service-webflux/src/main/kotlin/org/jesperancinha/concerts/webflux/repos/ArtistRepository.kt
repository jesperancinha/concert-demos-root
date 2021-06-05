package org.jesperancinha.concerts.webflux.repos

import org.jesperancinha.concerts.webflux.model.Artist
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface ArtistRepository : ReactiveCrudRepository<Artist, Long>
