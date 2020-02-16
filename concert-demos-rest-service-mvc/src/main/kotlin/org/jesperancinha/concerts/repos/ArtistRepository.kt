package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Artist
import org.springframework.data.repository.CrudRepository

interface ArtistRepository : CrudRepository<Artist, Long>
