package org.jesperancinha.concerts.mvc.repos

import org.jesperancinha.concerts.mvc.model.Artist
import org.springframework.data.repository.CrudRepository

interface ArtistRepository : CrudRepository<Artist, Long>
