package org.jesperancinha.concerts.repos

import org.jesperancinha.concerts.model.Artist
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface ArtistRepository : CrudRepository<Artist, Long>
