package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Artist
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ArtistRepository : CrudRepository<Artist, Long>
